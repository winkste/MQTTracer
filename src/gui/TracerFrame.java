/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import myMqtt.MqttSubscriber;
import myMqtt.MyMqttClient;

/**
 *
 * @author stephan_wink
 */
public class TracerFrame extends javax.swing.JFrame {
    
    MyMqttClient client;
    PrintWriter printer = null;
    private FilterDialog filterDialog;
    private MqttSubscriber errSubs;
    private MqttSubscriber stdSubs;
    private MqttSubscriber infSubs;

    /**
     * Creates new form TracerFrame
     */
    public TracerFrame() 
    {
 //       client = MyMqttClient.getInstance();
        
        errSubs = TracerFrame.this.getMqttErrSubscriberTrace("err/#");
        stdSubs = TracerFrame.this.getMqttStdSubscriberTrace("std/#");
        infSubs = TracerFrame.this.getMqttInfoSubscriberTrace("inf/#");
                
        initComponents();
        scroller_jsp.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            if(autoscroll_jcb.isSelected())
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
        });
        
        filterDialog = new FilterDialog(new javax.swing.JFrame(), true);
        filterDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                filterDialog.setVisible(false);
            }
        });
        filterDialog.setVisible(false);
    }
    
    public TracerFrame(MyMqttClient client) 
    {
        this();
        this.client = client;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroller_jsp = new javax.swing.JScrollPane();
        textDisplay_jtp = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        filter_jb = new javax.swing.JButton();
        select_jb = new javax.swing.JButton();
        log_jtb = new javax.swing.JToggleButton();
        connect_jtb = new javax.swing.JToggleButton();
        autoscroll_jcb = new javax.swing.JCheckBox();
        clear_jb = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        publish_jb = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        topic_jtf = new javax.swing.JTextField();
        payload_jtf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        textDisplay_jtp.setBackground(new java.awt.Color(102, 102, 102));
        scroller_jsp.setViewportView(textDisplay_jtp);

        getContentPane().add(scroller_jsp, java.awt.BorderLayout.CENTER);

        filter_jb.setText("filter");
        filter_jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_jbActionPerformed(evt);
            }
        });

        select_jb.setText("select file");
        select_jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_jbActionPerformed(evt);
            }
        });

        log_jtb.setText("log...");
        log_jtb.setEnabled(false);
        log_jtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_jtbActionPerformed(evt);
            }
        });

        connect_jtb.setText("connect...");
        connect_jtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connect_jtbActionPerformed(evt);
            }
        });

        autoscroll_jcb.setText("autoscroll");

        clear_jb.setText("clear");
        clear_jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_jbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filter_jb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(select_jb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(log_jtb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(connect_jtb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(clear_jb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(autoscroll_jcb)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(connect_jtb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filter_jb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(select_jb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(log_jtb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addComponent(clear_jb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoscroll_jcb)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        publish_jb.setText("publish...");
        publish_jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publish_jbActionPerformed(evt);
            }
        });

        jLabel1.setText("payload:");

        topic_jtf.setText("enter topic...");

        payload_jtf.setText("enter optional payload...");

        jLabel2.setText("topic:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topic_jtf, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payload_jtf, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(publish_jb)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(publish_jb)
                    .addComponent(jLabel1)
                    .addComponent(topic_jtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(payload_jtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connect_jtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connect_jtbActionPerformed
        if(connect_jtb.isSelected())
        {

            connectClient(); 
        }
        else
        {
            disconnect();
        }
    }//GEN-LAST:event_connect_jtbActionPerformed

    private void filter_jbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_jbActionPerformed
        filterDialog.setVisible(true);
    }//GEN-LAST:event_filter_jbActionPerformed

    private void select_jbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_jbActionPerformed
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                printer = new PrintWriter(new FileWriter(file), true);
            } catch (IOException ex) {
                Logger.getLogger(TracerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(null != printer)
            {
                // logging enabled
                log_jtb.setEnabled(true);
            }
            else
            {
                // logging disabled
                log_jtb.setEnabled(false);
            }           
        } 
        else 
        {
            if(null != printer)
            {
                printer.close();
                printer = null;
            }
            log_jtb.setEnabled(false);          
        }
    }//GEN-LAST:event_select_jbActionPerformed

    private void log_jtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_jtbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_log_jtbActionPerformed

    private void publish_jbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publish_jbActionPerformed
        if(true == client.isConnected())
        {
            client.publish(topic_jtf.getText(), payload_jtf.getText());
        }
    }//GEN-LAST:event_publish_jbActionPerformed

    private void clear_jbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_jbActionPerformed
        textDisplay_jtp.setText("");
    }//GEN-LAST:event_clear_jbActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(null != this.client)
        {
            disconnect();
        }
    }//GEN-LAST:event_formWindowClosing


    public void Start() 
    {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TracerFrame.this.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox autoscroll_jcb;
    public javax.swing.JButton clear_jb;
    public javax.swing.JToggleButton connect_jtb;
    public javax.swing.JButton filter_jb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JToggleButton log_jtb;
    private javax.swing.JTextField payload_jtf;
    private javax.swing.JButton publish_jb;
    public javax.swing.JScrollPane scroller_jsp;
    public javax.swing.JButton select_jb;
    public javax.swing.JTextPane textDisplay_jtp;
    private javax.swing.JTextField topic_jtf;
    // End of variables declaration//GEN-END:variables

    private void SetMessage(String filter, String msg, Color msgColor)
    {
        Date d = new Date();
        SimpleDateFormat sdfmt = new SimpleDateFormat();
        String entryString;
       
        sdfmt.applyPattern( "dd.MM.yyyy hh:mm:ss:SSS" );                   
        entryString = sdfmt.format(d) +"\t"+ filter + "\t" + msg + "\n";
        
        Logging(entryString);
        
        if(true == filterDialog.CheckFilter(filter))
        {
            try 
            {
                Document doc = textDisplay_jtp.getDocument();
                StyleContext sc = StyleContext.getDefaultStyleContext();
                AttributeSet as = sc.addAttribute(sc.getEmptySet(), 
                StyleConstants.Foreground, msgColor);
                doc.insertString(doc.getLength(), entryString, as);
                //textDisplay_jtp.setCaretPosition(textDisplay_jtp.getCaretPosition()+textDisplay_jtp.getText().length());
            } catch(BadLocationException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    public MqttSubscriber getMqttErrSubscriberTrace(String filter) 
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
                SetMessage(filter, msg, Color.red);
                
                
            }

            @Override
            public void notify(String msg) 
            {
                SetMessage("unknown filter", msg, Color.red);
            }

            @Override
            public void disconnected() 
            {
                handleDisconnection();
            }
        });
    }
    
    public MqttSubscriber getMqttInfoSubscriberTrace(String filter) 
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
                SetMessage(filter, msg, Color.GRAY);             
            }

            @Override
            public void notify(String msg) 
            {
                SetMessage("unknown filter", msg, Color.GRAY);
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
        connect_jtb.setSelected(false);
        JOptionPane.showMessageDialog(this,
            "Error in MQTT connection, please restart!",
            "MQTT warning",
            JOptionPane.WARNING_MESSAGE);
    }
    
    private void connectClient()
    {        
        client.setSubscriber(errSubs);
        client.setSubscriber(infSubs);
        client.setSubscriber(stdSubs);
    }
    
    public MqttSubscriber getMqttStdSubscriberTrace(String filter) 
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
                SetMessage(filter, msg, Color.white);             
            }

            @Override
            public void notify(String msg) 
            {
                SetMessage("unknown filter", msg, Color.white);
            }

            @Override
            public void disconnected() 
            {
                handleDisconnection();
            }
        });
    }

    private void Logging(String entryString) 
    {
        if((null != printer) && log_jtb.isSelected())
        {   
            printer.println(entryString);
            printer.flush();
        }
    }

    private void disconnect() 
    {
        
        client.removeSubscriber(errSubs);
        client.removeSubscriber(infSubs);
        client.removeSubscriber(stdSubs);
    }
}
