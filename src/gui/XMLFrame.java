/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import myMqtt.MyMqttClient;

/**
 *
 * @author stephan_wink
 */
public class XMLFrame extends javax.swing.JFrame 
{
    private XmlFrameControl logic;
    private MyMqttClient client;

    /**
     * Creates new form XMLFrame
     */
    public XMLFrame() {
        initComponents();
        logic = new XmlFrameControl(this);
        devices_jli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);     
    }
    
    public XMLFrame(MyMqttClient client)
    {
        this();
        this.client = client;
        logic.setClient(client);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        publications_jcb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        publishButton_jb = new javax.swing.JButton();
        payload_jcb = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        devices_jli = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        subscriptions_jt = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        xmlFile_jtf = new javax.swing.JTextField();
        loadButton_jtf = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("topic:");

        publications_jcb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        publications_jcb.setEnabled(false);
        publications_jcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publications_jcbActionPerformed(evt);
            }
        });

        jLabel2.setText("payload:");

        publishButton_jb.setText("publish...");
        publishButton_jb.setEnabled(false);
        publishButton_jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publishButton_jbActionPerformed(evt);
            }
        });

        payload_jcb.setEditable(true);
        payload_jcb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        payload_jcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payload_jcbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(publications_jcb, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payload_jcb, 0, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(publishButton_jb, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(publications_jcb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(publishButton_jb)
                    .addComponent(payload_jcb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        devices_jli.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        devices_jli.setEnabled(false);
        devices_jli.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                devices_jliValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(devices_jli);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        subscriptions_jt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"std/s/fwident", "N39001TC"},
                {"std/s/fwversion", "001"},
                {"std/s/fwdescr", null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Topic", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        subscriptions_jt.setEnabled(false);
        subscriptions_jt.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(subscriptions_jt);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel3.setText("xml file:");

        xmlFile_jtf.setEditable(false);
        xmlFile_jtf.setText("jTextField2");

        loadButton_jtf.setText("load file...");
        loadButton_jtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButton_jtfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xmlFile_jtf, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadButton_jtf)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xmlFile_jtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(loadButton_jtf))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButton_jtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButton_jtfActionPerformed
        // TODO add your handling code here:
        logic.StartFileSelection(evt);       
    }//GEN-LAST:event_loadButton_jtfActionPerformed

    private void devices_jliValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_devices_jliValueChanged
        logic.ReactOnDeviceSelectionChanged(this.devices_jli.getSelectedIndex());
    }//GEN-LAST:event_devices_jliValueChanged

    private void publications_jcbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publications_jcbActionPerformed

        if(-1 != this.publications_jcb.getSelectedIndex())
        {
            logic.ReactOnTxTopicSelctionChanged(this.publications_jcb.getSelectedItem().toString());
        }
    }//GEN-LAST:event_publications_jcbActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        logic.ReactOnClosing();
    }//GEN-LAST:event_formWindowClosing

    private void publishButton_jbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publishButton_jbActionPerformed
        /*logic.ReactOnPublishRequest(this.publications_jcb.getSelectedItem().toString(), 
                                        this.payload_jtf.getText());*/
        //System.out.println("IDx: " + this.payload_jcb.getSelectedIndex() + "Text:" + this.payload_jcb.getSelectedItem().toString());
        //if(0 <= this.payload_jcb.getSelectedIndex())
        //{
            logic.ReactOnPublishRequest(this.publications_jcb.getSelectedItem().toString(), 
                                        this.payload_jcb.getSelectedItem().toString());
            //System.out.println("Filter: " + this.publications_jcb.getSelectedItem().toString()
            //                        + "Payload: " + this.payload_jcb.getSelectedItem().toString());
        //}
    }//GEN-LAST:event_publishButton_jbActionPerformed

    private void payload_jcbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payload_jcbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_payload_jcbActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (   ClassNotFoundException | InstantiationException | IllegalAccessException 
                    | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XMLFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new XMLFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> devices_jli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loadButton_jtf;
    private javax.swing.JComboBox<String> payload_jcb;
    private javax.swing.JComboBox<String> publications_jcb;
    private javax.swing.JButton publishButton_jb;
    private javax.swing.JTable subscriptions_jt;
    private javax.swing.JTextField xmlFile_jtf;
    // End of variables declaration//GEN-END:variables


    void SetXmlFileName(String xmlFile) 
    {
        this.xmlFile_jtf.setText(xmlFile);
    }

    void SetDeviceList(String[] list) 
    {
        this.devices_jli.setListData(list);   
        this.devices_jli.setSelectedIndex(0);
    }

    void SetSubscriptions(AbstractTableModel model) 
    {
        this.subscriptions_jt.setModel(model);
        this.subscriptions_jt.getColumnModel().getColumn(0).setHeaderValue("Topic");
        this.subscriptions_jt.getColumnModel().getColumn(1).setHeaderValue("Data");  
    }

    void SetPublications(String [] publications) 
    {
        if(null == publications || 0 == publications.length)
        {
            this.publications_jcb.setEnabled(false);
        }
        else
        {    
            this.publications_jcb.setSelectedIndex(0);
            this.publications_jcb.removeAllItems();
            for (String publication : publications) 
            {
                this.publications_jcb.addItem(publication);
            }
            this.publications_jcb.setEnabled(true);
        }
    }

    void Start() 
    {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            XMLFrame.this.setVisible(true);
        });
    }

    void setEnabledOfflineElements(boolean b) 
    {
        this.devices_jli.setEnabled(b);
        this.publications_jcb.setEnabled(b); 
    }

    void setEnabledOnlineElements(boolean b) 
    {
        this.publishButton_jb.setEnabled(b);
    }

    TableModel GetTableModel() 
    {
        return(this.subscriptions_jt.getModel());
    }

    void SetPayloads(List<String> payloads) 
    {
        this.payload_jcb.setSelectedIndex(0);
        this.payload_jcb.removeAllItems();
        if(null != payloads)
        {
            
            for(int idx = 0; idx < payloads.size(); idx++)
            {
                this.payload_jcb.addItem(payloads.get(idx));
            }
        }
        this.payload_jcb.addItem("manual input...");
    }
    
    
}
