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
        String payload = "";
        while(0 != payloads.size())
        {
            payload = payload.concat(", " + payloads.remove(0));
        }
        this.frame.SetPayload(payload);
    }

    void UpdateControlsStatus()
    {
        if(true == fileLoaded)
        {
            this.frame.setEnabledOfflineElements(true);
            if(true == connected)
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
        
    }

    private void Subscribe(String[] subsArr) 
    {
        
    }
}
