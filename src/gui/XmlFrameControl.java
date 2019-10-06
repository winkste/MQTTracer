/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.MyXml;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;

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

    XmlFrameControl(XMLFrame frame) 
    {
        xmlFile = null;
        this.frame = frame;
        myXml = null;
        selectedDevice = 999;
    }

    void StartFileSelection(ActionEvent evt) 
    {
        JFileChooser chooser_jfc = new JFileChooser();
        int dialogResult_i;
        
        dialogResult_i = chooser_jfc.showOpenDialog(null);
        
        if(JFileChooser.APPROVE_OPTION == dialogResult_i)
        {
            xmlFile = chooser_jfc.getSelectedFile();
            frame.SetXmlFileName(xmlFile.getPath());
            myXml = new MyXml(xmlFile);
            myXml.Create();
            frame.SetDeviceList(myXml.GetDeviceList());
            frame.EnableControls();
        }
        else
        {   
            frame.DisableControls();
        }
    }

    void ReactOnDeviceSelectionChanged(int selectedIndex) 
    {
        if(selectedIndex != this.selectedDevice)
        {
            this.selectedDevice = selectedIndex;
            // at this point the subscription for the device is a publication send from the dialog
            this.frame.SetPublications(myXml.GetSubscriptionsForDevice(this.selectedDevice));
            // at this point the publication from the device is subscribed by the dialog
            this.frame.SetSubscriptions((myXml.GetPublicationsForDevice(this.selectedDevice)));
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
}
