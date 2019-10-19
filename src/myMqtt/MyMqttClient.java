/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myMqtt;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

/**
 *
 * @author stephan_wink
 */
public class MyMqttClient implements MqttCallback 
{
    private MqttClient client = null;
    private List<MqttSubscriber> subsList = new ArrayList<>();
    private String address;
    private String identifier;
    private String user;
    private String pwd;

    public void setUser(String user) 
    {
        this.user = user;
    }

    public void setPassword(String pwd) 
    {
        this.pwd = pwd;
    }
    
    private static final class InstanceHolder {
        static final MyMqttClient INSTANCE = new MyMqttClient();
    }
    
    private MyMqttClient()
    {
        this.address = "localhost";
        this.identifier = "localPC";
        this.user = "myUser";
        this.pwd = "pwd";
    }
    
    public static MyMqttClient getInstance () {
        return InstanceHolder.INSTANCE;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public boolean isConnected(){
        if(null != client)
        {
            return(client.isConnected());
        }
        else
        {
            return(false);
        }
    }
    
    public void connectClient()
    {        
        if(null == client)
        {
            try{
                client = new MqttClient(address, identifier);
                client.setCallback(this);
                MqttConnectOptions opts = new MqttConnectOptions();
                opts.setCleanSession(true);
                opts.setUserName(this.user);
                opts.setPassword(this.pwd.toCharArray());
                //client.connect(opts);
                IMqttToken token = client.connectWithResult(opts);
                token.waitForCompletion();
            } catch (MqttException ex) {
                Logger.getLogger(MyMqttClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            if(client.isConnected() == true)
            {
                try {
                    client.disconnect();
                    client = null;
                    client = new MqttClient(address, identifier);
                    client.setCallback(this);
                    client.connect();
                } catch (MqttException ex) {
                    Logger.getLogger(MyMqttClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void disconnect()
    {
        try {
            client.disconnect();
            for(int i = 0; i<subsList.size(); i++)
                removeSubscriber(subsList.remove(i));
            client = null;
            subsList = new ArrayList<MqttSubscriber>();
            } catch (MqttException ex) {
                    Logger.getLogger(MyMqttClient.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void setSubscriber(MqttSubscriber subs)
    {
        subsList.add(subs);
        try {
             IMqttToken token = client.subscribeWithResponse(subs.getFilter());
             //System.out.println(token.getGrantedQos() + " " + token.isComplete());
        } catch (MqttException ex) {
            Logger.getLogger(MyMqttClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeSubscriber(MqttSubscriber subs)
    {
        subsList.remove(subs);
    }

    @Override
    public void connectionLost(Throwable thrwbl) 
    {
        System.out.println("MyMqttClient: connection lost detected");
        for(int idx = 0; idx < subsList.size(); idx++)
        {
            // notify all subscriber that connection was lost
            subsList.get(idx).disconnected();
        }
    }

    @Override
    public void messageArrived(String filter, MqttMessage mm) throws Exception 
    {
        //System.out.println("Filter: " + filter + "   Msg: " + new String(mm.getPayload()));
        for(int idx = 0; idx < subsList.size(); idx++)
        {
            if(subsList.get(idx).getFilter().equals(filter))
            {
                subsList.get(idx).notify(new String(filter), new String(mm.getPayload()));
            }
            else if(-1 != subsList.get(idx).getFilter().indexOf("#"))
            {
                String subTopic = subsList.get(idx).getFilter();
                subTopic = subTopic.substring(0, subTopic.indexOf("#") - 1);
                if(filter.startsWith(subTopic))
                {
                    subsList.get(idx).notify(new String(filter), new String(mm.getPayload()));
                    //System.out.println("topic found with #");
                }
                
            }
        }      
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public MqttPublisher createPublisher(String filter)
    {
        return(new MqttPublisher(){
            @Override
            public String getFilter() {
                return filter;
            }

            @Override
            public void publish(String msg) {
                try {
                    MqttMessage message = new MqttMessage(msg.getBytes());
                    client.publish(this.getFilter(), message);
                } catch (MqttException ex) {
                    Logger.getLogger(MyMqttClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void publish(String filter, String msg)
    {
        try {
            MqttMessage message = new MqttMessage(msg.getBytes());
            client.publish(filter, message);
        } catch (MqttException ex) {
            Logger.getLogger(MyMqttClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
