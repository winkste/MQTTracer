/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.MyXml;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import myMqtt.MqttSubscriber;
import myMqtt.MyMqttClient;

/**
 *
 * @author stephan_wink
 */
public class XmlFrameControl 
{
    private File xmlFile;
    private XMLFrame frame;
    private MyXml myXml;
    private int selectedDevice;
    private boolean connected;
    private boolean fileLoaded;
    private MyMqttClient client;
    private List <MqttSubscriber> subsList;
    
    
    XmlFrameControl(XMLFrame frame) 
    {
        xmlFile = null;
        this.frame = frame;
        myXml = null;
        selectedDevice = 999;
        connected = false;
        fileLoaded = false;
        subsList = new ArrayList<>();
    }

    void StartFileSelection(ActionEvent evt) 
    {
        JFileChooser chooser_jfc = new JFileChooser();
        int dialogResult_i;
        
        dialogResult_i = chooser_jfc.showOpenDialog(null);
        
        if(JFileChooser.APPROVE_OPTION == dialogResult_i)
        {
            this.xmlFile = chooser_jfc.getSelectedFile();
            this.frame.SetXmlFileName(xmlFile.getPath());
            this.myXml = new MyXml(xmlFile);
            this.fileLoaded = myXml.Create();
            this.frame.SetDeviceList(myXml.GetDeviceList());
            this.selectedDevice = 999;
            ReactOnDeviceSelectionChanged(0);
        }
        else
        {   
            this.fileLoaded = false;
        }
        UpdateControlsStatus();
    }

    void ReactOnDeviceSelectionChanged(int selectedIndex) 
    {
        if(0 > selectedIndex)
        {
            selectedIndex = 0;
        }
        if(selectedIndex != this.selectedDevice)
        {
            this.selectedDevice = selectedIndex;
            // at this point the subscription for the device is a publication send from the dialog
            this.frame.SetPublications(myXml.GetSubscriptionsForDevice(this.selectedDevice));
            // at this point the publication from the device is subscribed by the dialog
            this.frame.SetSubscriptions((myXml.GetPublicationsForDevice(this.selectedDevice)));
            
            String [] subsArr = myXml.GetPublicationsForDeviceComplete(selectedDevice);
            UnsubscribeAll();
            Subscribe(subsArr);            
        }
    }

    void ReactOnTxTopicSelctionChanged(String topic) 
    {
        List <String> payloads = this.myXml.GetPayload(topic);
        
        this.frame.SetPayloads(payloads);
    }

    void UpdateControlsStatus()
    {
        if(true == fileLoaded)
        {
            this.frame.setEnabledOfflineElements(true);
            if(null != client && client.isConnected())
            {
                this.frame.setEnabledOnlineElements(true);
            }
        }
        else
        {
            this.frame.setEnabledOfflineElements(false);
            this.frame.setEnabledOnlineElements(false);
        }
    }

    void setClient(MyMqttClient client) 
    {
        this.client = client;
    }

    private void UnsubscribeAll() 
    {
        if(null != this.client)
        {
            while(!this.subsList.isEmpty())
            {
                this.client.removeSubscriber(this.subsList.remove(0));
                
            }
        }
    }

    private void Subscribe(String[] subsArr) 
    {
        MqttSubscriber tempSubs;
        if(null != this.client && null != subsArr)
        {
            for(int idx = 0; idx < subsArr.length; idx++)
            {
                tempSubs = getMqttSubscriber(subsArr[idx]);
                this.subsList.add(tempSubs);
                this.client.setSubscriber(tempSubs);
            }
        } 
    }
    
    public MqttSubscriber getMqttSubscriber(String filter) 
    {
        return(new MqttSubscriber(){
            @Override
            public String getFilter() 
            {
                return(filter);
            }

            @Override
            public void notify(String filter, String msg) 
            {
                //System.out.println("message received: " + filter + "--" + msg);
                for(int idx = 0; idx < XmlFrameControl.this.subsList.size(); idx++)
                {
                    String var = XmlFrameControl.this.subsList.get(idx).getFilter();
                    if(var.contentEquals(filter))
                    {
                        //System.out.println("old:" + XmlFrameControl.this.frame.GetTableModel().getValueAt(idx, 1));
                        XmlFrameControl.this.frame.GetTableModel().setValueAt(msg, idx, 1);
                        //XmlFrameControl.this.frame.GetTableModel().fireTableDataChanged();
                                
                        //System.out.println("new:" + XmlFrameControl.this.frame.GetTableModel().getValueAt(idx, 1));
                    }
                }
                
            }

            @Override
            public void notify(String msg) 
            {
                System.out.println("message received: " + filter + "--" + msg);
            }

            @Override
            public void disconnected() 
            {
                handleDisconnection();
            }
        });
    }
    
    private void handleDisconnection()
    {
        /*JOptionPane.showMessageDialog(this,
            "Error in MQTT connection, please restart!",
            "MQTT warning",
            JOptionPane.WARNING_MESSAGE);*/
    }

    void ReactOnClosing() 
    {
        UnsubscribeAll();
    }

    void ReactOnPublishRequest(String subsId, String payload) 
    {
        String filter;
        
        filter = this.myXml.GetSubscriptionById(this.selectedDevice, subsId);
        
        if(true == client.isConnected())
        {
            client.publish(filter, payload);
        }
    }
}
