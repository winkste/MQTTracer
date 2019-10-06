/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author stephan_wink
 */
public class MyXml 
{
    private List <Topic> topics = null;
    private List <Capability> capas = null;
    private List <Device> devices = null;
    private final File xmlFile;
    
    public MyXml(File xmlFile)
    {
        this.xmlFile = xmlFile; 
    }
    
    public boolean Create()
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;
        try 
        {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.xmlFile);
            doc.getDocumentElement().normalize();
            
            //only for debug: PrintXmlElements(doc);
            CreateTopicsFromXml(doc);
            CreateCapabilitiesFromXml(doc);
            CreateDevicesFromXml(doc);
         
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(MyXml.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return(true);
    }
    
    public String [] GetDeviceList()
    {
        String [] list = new String[devices.size()];
        
        for(int idx = 0; idx < devices.size(); idx++)
        {
            list[idx] = devices.get(idx).GetId() + "->" + devices.get(idx).GetName();
        }
            
        return(list);
    }
    
    public AbstractTableModel GetPublicationsForDevice(int device)
    {
        List <String>list = new ArrayList<>();
        String [] vectTopics;
        
        int capaIdx = 0;
        while(null != devices.get(device).GetCapIdAtIdx(capaIdx))
        {
            String capability = devices.get(device).GetCapIdAtIdx(capaIdx);
            
            for(int idx = 0; idx < capas.size(); idx++)
            {
                if(0 == capability.compareTo(capas.get(idx).GetId()))
                {
                    int jdx = 0;
                    while(null != capas.get(idx).GetPublications(jdx))
                    {
                        list.add(capas.get(idx).GetPublications(jdx));
                        jdx++;
                    }
                }
            }  
            capaIdx++;
        }
        
        vectTopics = new String[list.size()];
        for(int idx = 0; idx < list.size(); idx++)
        {
            vectTopics[idx] = list.get(idx);
        }
                 
        return(new myTableModel(vectTopics));
    }
    
    public List<String> GetPayload(String topic)
    {
        List payloads = new ArrayList<>();
        
        for(int idx = 0; idx < topics.size(); idx++)
        {
            if(topics.get(idx).GetId().startsWith(topic))
            {
                int jdx = 0;
                while(null != topics.get(idx).GetPayloadAt(jdx))
                {
                    payloads.add(topics.get(idx).GetPayloadAt(jdx));
                    jdx++;
                }
            }
        }


        return(payloads);
    }

    public String [] GetSubscriptionsForDevice(int device) 
    {   
        List <String>list = new ArrayList<>();
        String [] vectTopics;
        
        int capaIdx = 0;
        while(null != devices.get(device).GetCapIdAtIdx(capaIdx))
        {
            String capability = devices.get(device).GetCapIdAtIdx(capaIdx);
            
            for(int idx = 0; idx < capas.size(); idx++)
            {
                if(0 == capability.compareTo(capas.get(idx).GetId()))
                {
                    int jdx = 0;
                    while(null != capas.get(idx).GetSubscriptions(jdx))
                    {
                        list.add(capas.get(idx).GetSubscriptions(jdx));
                        jdx++;
                    }
                }
            }  
            capaIdx++;
        }
        
        vectTopics = new String[list.size()];
        for(int idx = 0; idx < list.size(); idx++)
        {
            vectTopics[idx] = list.get(idx);
        }
        
        return(vectTopics);      
    }

    private void PrintXmlElements(Document doc) 
    {
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());        
        NodeList nList = doc.getElementsByTagName("dev");
        System.out.println("---Devices----------------------------");        
        for (int temp = 0; temp < nList.getLength(); temp++) 
        {
           Node nNode = nList.item(temp);
           System.out.println("Current Element :" + nNode.getNodeName());

           if (nNode.getNodeType() == Node.ELEMENT_NODE) 
           {
              Element eElement = (Element) nNode;
              System.out.println("Device id: " 
                 + eElement.getAttribute("id"));
              System.out.println("Name: " 
                 + eElement
                 .getElementsByTagName("name")
                 .item(0)
                 .getTextContent());
              System.out.println("Type: " 
                 + eElement
                 .getElementsByTagName("type")
                 .item(0)
                 .getTextContent());
              System.out.println("Location: " 
                 + eElement
                 .getElementsByTagName("locat")
                 .item(0)
                 .getTextContent());
              System.out.println("Firmware id: " 
                 + eElement
                 .getElementsByTagName("fw_id")
                 .item(0)
                 .getTextContent());
              System.out.println("FW Version: " 
                 + eElement
                 .getElementsByTagName("fw_ver")
                 .item(0)
                 .getTextContent());
              System.out.println("FW Description: " 
                 + eElement
                 .getElementsByTagName("fw_desc")
                 .item(0)
                 .getTextContent());

              NodeList caps = eElement.getElementsByTagName("cap_id");
              for(int idx = 0; idx < caps.getLength(); idx++)
              {
               System.out.println("Capability: " + caps.item(idx).getTextContent());
              }

           }
           System.out.println("-------------------------------");
        }

        NodeList capas = doc.getElementsByTagName("cap");
        System.out.println("---Capabilities----------------------------");        
        for (int temp = 0; temp < capas.getLength(); temp++) 
        {
           Node nNode = capas.item(temp);
           System.out.println("Current Element :" + nNode.getNodeName());

           if (nNode.getNodeType() == Node.ELEMENT_NODE) 
           {
                Element eElement = (Element) nNode;
                System.out.println("Cap id: " + eElement.getAttribute("id"));
                System.out.println("Name: " 
                 + eElement
                 .getElementsByTagName("name")
                 .item(0)
                 .getTextContent());

                NodeList topicsRx = eElement.getElementsByTagName("topic_rx");
                for(int idx = 0; idx < topicsRx.getLength(); idx++)
                {
                 System.out.println("Topic Rx: " + topicsRx.item(idx).getTextContent());
                }
                NodeList topicsTx = eElement.getElementsByTagName("topic_tx");
                for(int idx = 0; idx < topicsTx.getLength(); idx++)
                {
                 System.out.println("Topic Tx: " + topicsTx.item(idx).getTextContent());
                }
                NodeList ext = eElement.getElementsByTagName("ext");
                for(int idx = 0; idx < ext.getLength(); idx++)
                {
                 System.out.println("Extension " + ext.item(idx).getTextContent());
                }
           }
           System.out.println("-------------------------------");
        }

        NodeList topics = doc.getElementsByTagName("topic");
        System.out.println("---Topics----------------------------");        
        for (int temp = 0; temp < topics.getLength(); temp++) 
        {
           Node nNode = topics.item(temp);
           System.out.println("Current Element :" + nNode.getNodeName());

           if (nNode.getNodeType() == Node.ELEMENT_NODE) 
           {
                Element eElement = (Element) nNode;
                System.out.println("Topic id: " + eElement.getAttribute("id"));
                System.out.println("Channel: " 
                 + eElement
                 .getElementsByTagName("chan")
                 .item(0)
                 .getTextContent());

                NodeList payload = eElement.getElementsByTagName("payload");
                for(int idx = 0; idx < payload.getLength(); idx++)
                {
                 System.out.println("Payload " + payload.item(idx).getTextContent());
                }
           }
           System.out.println("-------------------------------");
        }
    }

    private void CreateTopicsFromXml(Document doc) 
    {
        topics = new ArrayList<>();      
        NodeList xmlNodes = doc.getElementsByTagName("topic");
        
        for (int temp = 0; temp < xmlNodes.getLength(); temp++) 
        {
           Node nNode = xmlNodes.item(temp);
           if (nNode.getNodeType() == Node.ELEMENT_NODE) 
           {
                Element eElement = (Element) nNode;
                String id = eElement.getAttribute("id");
                String chan = eElement.getElementsByTagName("chan").item(0).getTextContent();
                               
                String [] strPayload;
                NodeList payload = eElement.getElementsByTagName("payload");
                if(0 != payload.getLength())
                {
                    strPayload = new String[payload.getLength()];
                    for(int idx = 0; idx < payload.getLength(); idx++)
                    {
                        strPayload[idx] = payload.item(idx).getTextContent();
                    }
                    
                    topics.add(new Topic(id, chan, strPayload));
                }
                else
                {
                    topics.add(new Topic(id, chan));
                }               
            }
        }       
    }

    private void CreateCapabilitiesFromXml(Document doc) 
    {
        capas = new ArrayList<>();
        NodeList nodeCapas = doc.getElementsByTagName("cap");
       
        for(int temp = 0; temp < nodeCapas.getLength(); temp++) 
        {
           Node nNode = nodeCapas.item(temp);

           if(nNode.getNodeType() == Node.ELEMENT_NODE) 
           {
                Element eElement = (Element) nNode;
                String id = eElement.getAttribute("id");
                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                
                NodeList nodeRxTopics = eElement.getElementsByTagName("topic_rx");
                String [] rxTopics = new String[nodeRxTopics.getLength()];
                for(int idx = 0; idx < nodeRxTopics.getLength(); idx++)
                {
                    rxTopics[idx] = nodeRxTopics.item(idx).getTextContent();
                }
                
                NodeList nodeTxTopics = eElement.getElementsByTagName("topic_tx");
                String [] txTopics = new String[nodeTxTopics.getLength()];
                for(int idx = 0; idx < nodeTxTopics.getLength(); idx++)
                {
                    txTopics[idx] = nodeTxTopics.item(idx).getTextContent();
                }
                
                NodeList nodeExt = eElement.getElementsByTagName("ext");
                String [] extensions = new String[nodeExt.getLength()];
                for(int idx = 0; idx < nodeExt.getLength(); idx++)
                {
                    extensions[idx] = nodeExt.item(idx).getTextContent();
                }
                
                NodeList nodeCapRef = eElement.getElementsByTagName("cap_ref");
                String [] capRef = new String[nodeCapRef.getLength()];
                for(int idx = 0; idx < nodeCapRef.getLength(); idx++)
                {
                    capRef[idx] = nodeCapRef.item(idx).getTextContent();
                }
                
                capas.add((new Capability(id, name, rxTopics, txTopics, capRef, extensions)));
           }
        }
        
    }

    private void CreateDevicesFromXml(Document doc) 
    {
        devices = new ArrayList<>();     
        NodeList nodeDevices = doc.getElementsByTagName("dev"); 
        
        for (int temp = 0; temp < nodeDevices.getLength(); temp++) 
        {
           Node nNode = nodeDevices.item(temp);

           if (nNode.getNodeType() == Node.ELEMENT_NODE) 
           { 
                Element eElement = (Element) nNode;
                String id = eElement.getAttribute("id");
                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                String location = eElement.getElementsByTagName("locat").item(0).getTextContent();
                String fwId = eElement.getElementsByTagName("fw_id").item(0).getTextContent();
                String fwVer = eElement.getElementsByTagName("fw_ver").item(0).getTextContent();
                String fwDesc = eElement.getElementsByTagName("fw_desc").item(0).getTextContent();
             
              
                NodeList nodeCaps = eElement.getElementsByTagName("cap_id");
                String [] caps = new String[nodeCaps.getLength()];
                for(int idx = 0; idx < nodeCaps.getLength(); idx++)
                {
                    caps[idx] = nodeCaps.item(idx).getTextContent();
                }
                
                devices.add(new Device(id, name, type, location, fwId, fwVer, fwDesc, caps));

           }
        }
    }
    
    class myTableModel extends AbstractTableModel
    {
        String [] topics;
        String [] values;
        
        public myTableModel(String [] topics)
        {
            this.topics = topics.clone();
            values = new String[topics.length];
            for(int idx = 0; idx < this.topics.length; idx++)
            {
                this.values[idx] = "na";
            }
        }

        @Override
        public int getRowCount() 
        {
            return(this.topics.length);
        }

        @Override
        public int getColumnCount() 
        {
            return(2);    
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) 
        {
            if((0 == columnIndex) && (this.topics.length > rowIndex))
            {
                return(this.topics[rowIndex]);
            }
            else if((1 == columnIndex) && (this.topics.length > rowIndex))
            {
                return(this.values[rowIndex]);
            }
            else
            {
                return(null);
            }
        }
        
        @Override
        public void setValueAt(Object value, int row, int col) 
        {
            if(1 == col)
            {
                if(row < this.values.length)
                {
                    this.values[row] = (String)value;
                }
            }
       
        }
    }
    
}
