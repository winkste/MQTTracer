/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launch;

import gui.MqttStarter;
import gui.TracerFrame;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myMqtt.MyMqttClient;

/**
 *
 * @author stephan_wink
 */
public class Launcher 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        /*MyMqttClient client = MyMqttClient.getInstance();
                client.setAddress("tcp://192.168.178.45:1883");
                client.setIdentifier("macBook_pro");
                client.connectClient();
                
        TracerFrame tracer = new TracerFrame(client);
        tracer.setTitle("MQTTracer");*/
                
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getSystemLookAndFeelClassName() );
        } catch( Exception e ) { e.printStackTrace(); }
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TracerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TracerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TracerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TracerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tracer.setVisible(true);
            }
        });*/
        
        java.net.InetAddress localMachine;
        String hostName = "unknown";
        try {
            localMachine = java.net.InetAddress.getLocalHost();
            System.out.println("Hostname of local machine: " + localMachine.getHostName());
            hostName = localMachine.getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       MqttStarter start = new MqttStarter(hostName);
       start.Start();
    }
    
}
