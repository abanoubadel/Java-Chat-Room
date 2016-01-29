package client.panels;

import client.Chat;
import client.ChatApplication;
import client.list.ContactsListCellRender;
import client.list.ContactsListDataModel;
import db.UserAccount;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Consumer;
import javax.swing.ImageIcon;

/**
 *
 * @author mahmoud_fouad
 */
public class ContactsPanel extends javax.swing.JPanel {

    private ContactsListDataModel contactsListDataModel;
    private ChatApplication parent;

    private Chat defalutChat;
    private ArrayList<Chat> chats = new ArrayList<>();

    public ContactsPanel() {
        initComponents();
    }

    public void getInitContacts() {
        parent.getClientSocketConnection().sendGetContactList();
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
        contactsList = new javax.swing.JList<>();
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

        contactsList.setModel(contactsListDataModel = new ContactsListDataModel());
        contactsList.setCellRenderer(new ContactsListCellRender ());
        contactsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactsListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(contactsList);

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
        if (evt.getStateChange() == ItemEvent.SELECTED) {
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
        }
    }//GEN-LAST:event_StateComboBoxItemStateChanged

    private void defaultChatRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultChatRoomButtonActionPerformed
        defalutChat.setVisible(true);
    }//GEN-LAST:event_defaultChatRoomButtonActionPerformed

    private void contactsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactsListMouseClicked
        if (evt.getClickCount() == 2 && contactsList.getSelectedValue() instanceof UserAccount) {
            UserAccount account = ((UserAccount) contactsList.getSelectedValue());

            if (account.getStatus() == UserAccount.AVAILABLE || account.getStatus() == UserAccount.BUSY) {
                Vector<Integer> id = new Vector<>();
                id.add(account.getId());

                Chat room = isOpended(id);
                if (room == null) {
                    Chat chat = new Chat(parent, id);
                    chats.add(chat);

                    chat.setVisible(true);
                } else {
                    room.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_contactsListMouseClicked

    public Chat isOpended(Vector<Integer> ids) {
        for (int i = 0; i < chats.size(); i++) {
            Chat room = chats.get(i);
            if (room.getReceveList().containsAll(ids)) {
                return room;
            }
        }

        return null;
    }

    public ContactsListDataModel getContactsListDataModel() {
        return contactsListDataModel;
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

    public void distroyAllChatRoom() {
        defalutChat.setVisible(false);
        defalutChat.invalidate();
        defalutChat = null;

        chats.forEach(new Consumer<Chat>() {
            @Override
            public void accept(Chat t) {
                t.setVisible(false);
                t.invalidate();
            }
        });
        chats.clear();
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void switchMessage(String msg, Vector<Integer> receiver) {
        Chat room = isOpended(receiver);

        if (room != null) {
            room.appenMessage(msg);
        } else {
            //case not opened
            Chat chat = new Chat(parent, receiver);
            chats.add(chat);
            chat.appenMessage(msg);
            chat.setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NameLabel;
    private javax.swing.JComboBox<String> StateComboBox;
    private javax.swing.JList<Object> contactsList;
    private javax.swing.JButton defaultChatRoomButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel myStateLabel;
    private javax.swing.JLabel userImageLabel;
    // End of variables declaration//GEN-END:variables
}
