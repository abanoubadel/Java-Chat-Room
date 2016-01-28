package client.panels;

import client.Chat;
import client.ChatApplication;
import client.list.ContactsListCellRender;
import client.list.ContactsListDataModel;
import db.UserAccount;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;

/**
 *
 * @author mahmoud_fouad
 */
public class ContactsPanel extends javax.swing.JPanel {

    private ContactsListDataModel contactsListDataModel;
    private ChatApplication parent;
    private ArrayList<UserAccount> allContacts = new ArrayList<>();
    private Chat defalutChat;
    private ArrayList<Chat> chats = new ArrayList<>();

    public ContactsPanel() {
        initComponents();
    }

    public void startTimer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    parent.getClientSocketConnection().sendGetContactList();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public ContactsPanel(ChatApplication parent) {
        this.parent = parent;

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userImageLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        myStateLabel = new javax.swing.JLabel();
        StateComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        defaultChatRoomButton = new javax.swing.JButton();

        userImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/1-1.PNG"))); // NOI18N

        NameLabel.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        NameLabel.setText("Name");

        myStateLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ava.PNG"))); // NOI18N

        StateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Busy", "Hidden" }));
        StateComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                StateComboBoxItemStateChanged(evt);
            }
        });

        jList1.setModel(contactsListDataModel = new ContactsListDataModel());
        jList1.setCellRenderer(new ContactsListCellRender ());
        jScrollPane1.setViewportView(jList1);

        defaultChatRoomButton.setText("Show Shat Room");
        defaultChatRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultChatRoomButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userImageLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(myStateLabel)
                                .addGap(18, 18, 18)
                                .addComponent(StateComboBox, 0, 212, Short.MAX_VALUE))))
                    .addComponent(defaultChatRoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userImageLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(myStateLabel)
                            .addComponent(StateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(defaultChatRoomButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void StateComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_StateComboBoxItemStateChanged
        ImageIcon imageIcon = null;

        switch (StateComboBox.getSelectedIndex()) {
            case 0:
                imageIcon = new ImageIcon(getClass().getResource("/resources/ava.PNG"));
                parent.getClientSocketConnection().sendChangeState(UserAccount.AVAILABLE);
                break;
            case 1:
                imageIcon = new ImageIcon(getClass().getResource("/resources/red.PNG"));
                parent.getClientSocketConnection().sendChangeState(UserAccount.BUSY);
                break;
            case 2:
                imageIcon = new ImageIcon(getClass().getResource("/resources/gray.PNG"));
                parent.getClientSocketConnection().sendChangeState(UserAccount.HIDDEN);
        }

        myStateLabel.setIcon(imageIcon);
    }//GEN-LAST:event_StateComboBoxItemStateChanged

    private void defaultChatRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultChatRoomButtonActionPerformed
        defalutChat.setVisible(true);
    }//GEN-LAST:event_defaultChatRoomButtonActionPerformed

    public void updateContactList() {
        Vector<Object> ava = new Vector<>();
        Vector<Object> bus = new Vector<>();
        Vector<Object> unav = new Vector<>();

        for (int i = 0; i < allContacts.size(); i++) {
            UserAccount con = allContacts.get(i);

            switch (con.getStatus()) {
                case UserAccount.AVAILABLE:
                    ava.add(con);
                    break;
                case UserAccount.BUSY:
                    bus.add(con);
                    break;
                default:
                    unav.add(con);
            }
        }
        contactsListDataModel.clear();

        contactsListDataModel.addToList("    available");
        contactsListDataModel.addAll(ava);
        contactsListDataModel.addToList("      busy");
        contactsListDataModel.addAll(bus);
        contactsListDataModel.addToList("    unavailable");
        contactsListDataModel.addAll(unav);
        //refresh
        contactsListDataModel.reload();
        //clear
        allContacts.clear();
    }

    public void setAllContacts(ArrayList<UserAccount> allContacts) {
        this.allContacts = allContacts;
    }

    public ArrayList<UserAccount> getAllContacts() {
        return allContacts;
    }

    public void setUserFullName(String name) {
        NameLabel.setText(name);
    }

    public Chat getDefalutChat() {
        return defalutChat;
    }

    public void createDefalutChatRoom() {
        defalutChat = new Chat(parent);
    }

    public void distroyDefalutChatRoom() {
        defalutChat = null;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void switchMessage(String msg, Vector<Integer> receiver) {
        for (int i = 0; i < chats.size(); i++) {
            Chat room = chats.get(i);
            if (room.getReceveList().containsAll(receiver)) {
                room.appenMessage(msg);
                return;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NameLabel;
    private javax.swing.JComboBox<String> StateComboBox;
    private javax.swing.JButton defaultChatRoomButton;
    private javax.swing.JList<Object> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel myStateLabel;
    private javax.swing.JLabel userImageLabel;
    // End of variables declaration//GEN-END:variables
}
