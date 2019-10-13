/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.SwingWorker;
import myMqtt.MyMqttClient;

/**
 *
 * @author stephan_wink
 */
public class MqttStarter extends javax.swing.JFrame {
    
    private MyMqttClient client;
    private int heartBeat;
    private String hostname = "default";

    /**
     * Creates new form MqttStarter
     */
    public MqttStarter() {
        this.client = MyMqttClient.getInstance();
        initComponents();
        new MqttStarter.DataCollector().execute();
        this.heartBeat = 0;
        hostname = "default";
    }

    public MqttStarter(String hostname) {
        this();
        this.hostname = hostname;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        log_jta = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        connect_jtb = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        startTracer_jb = new javax.swing.JButton();
        startXml_jb = new javax.swing.JButton();
        startSequencer_jb = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        log_jta.setBackground(new java.awt.Color(102, 102, 102));
        log_jta.setColumns(20);
        log_jta.setForeground(new java.awt.Color(255, 255, 255));
        log_jta.setRows(5);
        jScrollPane1.setViewportView(log_jta);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mqtt_icon_128px.png"))); // NOI18N

        connect_jtb.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        connect_jtb.setText("connect...");
        connect_jtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connect_jtbActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        startTracer_jb.setText("MQTTracer");
        startTracer_jb.setEnabled(false);
        startTracer_jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startTracer_jbActionPerformed(evt);
            }
        });
        jPanel1.add(startTracer_jb);

        startXml_jb.setText("XMLFrame");
        startXml_jb.setEnabled(false);
        startXml_jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startXml_jbActionPerformed(evt);
            }
        });
        jPanel1.add(startXml_jb);

        startSequencer_jb.setText("MQTTSequencer");
        startSequencer_jb.setEnabled(false);
        startSequencer_jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSequencer_jbActionPerformed(evt);
            }
        });
        jPanel1.add(startSequencer_jb);

        jButton2.setText("unused...");
        jPanel1.add(jButton2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(connect_jtb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(connect_jtb, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connect_jtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connect_jtbActionPerformed
        if(connect_jtb.isSelected())
        {
            if(!client.isConnected())
            {
                client.setAddress("tcp://192.168.178.45:1883");
                client.setIdentifier(this.hostname);
                client.connectClient();
                EnableProgramStarts(true);
                this.log_jta.append(this.hostname + " connected to mqtt broker ...\n");
            }
        }
        else
        {
            if(client.isConnected())
            {
                // disconnect sequence
                EnableProgramStarts(false);
                client.disconnect();
                this.log_jta.append("disconnected from mqtt broker...\n");
            }
        }
    }//GEN-LAST:event_connect_jtbActionPerformed

    private void startTracer_jbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startTracer_jbActionPerformed
        TracerFrame tracer = new TracerFrame(client);
        tracer.Start();
        this.log_jta.append("trace dialog started...\n");
    }//GEN-LAST:event_startTracer_jbActionPerformed

    private void startXml_jbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startXml_jbActionPerformed
        XMLFrame xml = new XMLFrame(client);
        xml.Start();
        this.log_jta.append("xml dialog started...\n");
    }//GEN-LAST:event_startXml_jbActionPerformed

    private void startSequencer_jbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSequencer_jbActionPerformed
        this.log_jta.append("sequencer dialog started...\n");
    }//GEN-LAST:event_startSequencer_jbActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(client.isConnected())
            client.disconnect();
    }//GEN-LAST:event_formWindowClosing


    public void Start()
    {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MqttStarter.this.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton connect_jtb;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea log_jta;
    private javax.swing.JButton startSequencer_jb;
    private javax.swing.JButton startTracer_jb;
    private javax.swing.JButton startXml_jb;
    // End of variables declaration//GEN-END:variables

    private void EnableProgramStarts(boolean enable) {
        this.startTracer_jb.setEnabled(enable);
        this.startXml_jb.setEnabled(enable);
        this.startSequencer_jb.setEnabled(enable);
        
    }
    
    class DataCollector extends SwingWorker<Long, Object>
    {

        @Override
        protected Long doInBackground() throws Exception 
        {
            try 
            { 
                Thread.sleep(30000);
            } catch ( InterruptedException e ) { }
            new DataCollector().execute();
            return (0L);
        }
        
        @Override protected void done()
        {
            MqttStarter.this.heartBeat++;
            String topic = "std/dev90/s/gen/heart";
            if(true == MqttStarter.this.client.isConnected())
            {
                MqttStarter.this.client.publish(topic, Integer.toString(MqttStarter.this.heartBeat));
                MqttStarter.this.log_jta.append("published : " + topic + " " + MqttStarter.this.heartBeat + "\n");
            }
        }
    }
}
