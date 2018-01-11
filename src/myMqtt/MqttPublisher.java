/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myMqtt;

/**
 *
 * @author stephan_wink
 */
public interface MqttPublisher {
    
    public String getFilter();
    
    public void publish(String msg);
    
}
