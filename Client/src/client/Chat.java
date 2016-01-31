package client;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import message.NetMessage;

/**
 *
 * @author mahmoud_fouad
 */
public class Chat extends javax.swing.JFrame {

    private Vector<Integer> receveList;
    private ChatApplication parent;

    public Chat(ChatApplication parent) {
        this.parent = parent;
        initComponents();
    }

    public Chat(ChatApplication parent, Vector<Integer> receveList) {
        this.parent = parent;
        this.receveList = receveList;

        initComponents();
    }

    public Vector<Integer> getReceveList() {
        return receveList;
    }

    public void appenMessage(String msg) {
        chatTextArea.append(msg + "\n");
        
    }

    public void disableSend() {
        sendButton.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatTextArea = new javax.swing.JTextArea();
        msgTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setTitle("Chat APP");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chatTextArea.setEditable(false);
        chatTextArea.setColumns(20);
        chatTextArea.setRows(5);
        jScrollPane1.setViewportView(chatTextArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 438, 190));

        msgTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(msgTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 235, 353, 52));

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        getContentPane().add(sendButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 235, 73, 52));

        jButton1.setText("Save Session");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));
        jButton1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        if (!msgTextField.getText().equals("")) {
            parent.getClientSocketConnection().sendMessage(new NetMessage(NetMessage.NetMessageType.MESSAGE, msgTextField.getText(), receveList));
            msgTextField.setText("");
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void msgTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgTextFieldActionPerformed
        sendButtonActionPerformed(evt);
    }//GEN-LAST:event_msgTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //File myFile= new File("//home//iman//ITI_Study//Java //Project//SaveSession.txt');
            
            JFileChooser fc = new JFileChooser();
            if(fc.showSaveDialog(this) ==JFileChooser.APPROVE_OPTION)
                {
                    
                    String path = fc.getSelectedFile().getPath();
                    
                try {
                    FileOutputStream fos;
                    fos = new FileOutputStream(path);
                     byte[] b = chatTextArea.getText().getBytes();
                     fos.write(b);
                     fos.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                //Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                
                   
                }
                
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField msgTextField;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
