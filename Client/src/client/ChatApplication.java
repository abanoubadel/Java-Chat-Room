package client;

import client.panels.ContactsPanel;
import client.panels.SignupPanel;
import client.panels.loginPanel;
import com.alee.managers.notification.NotificationIcon;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotification;
import java.awt.CardLayout;

/**
 *
 * @author mahmoud_fouad
 */
public class ChatApplication extends javax.swing.JFrame {

    private CardLayout layout;
    private ClientSocketConnection clientSocketConnection;
    private WebNotification notification;

    public ChatApplication() {
        clientSocketConnection = new ClientSocketConnection(this);

        notification = new WebNotification();
        notification.setClickToClose(true);
        notification.setDisplayTime(3000);
        notification.setIcon(NotificationIcon.information);
        NotificationManager.setLocation(NotificationManager.SOUTH_EAST);

        initComponents();

        layout = (CardLayout) getContentPane().getLayout();

        switchView("login");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        messengerMenu = new javax.swing.JMenu();
        signoutMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        chatMenu = new javax.swing.JMenu();
        createChatMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        signupPanel = new SignupPanel(this)
        ;
        contactsPanel = new ContactsPanel(this)
        ;
        loginPanel = new loginPanel(this)
        ;

        messengerMenu.setText("File");

        signoutMenuItem.setLabel("Signout");
        messengerMenu.add(signoutMenuItem);
        messengerMenu.add(jSeparator2);

        exitMenuItem.setLabel("Exit");
        messengerMenu.add(exitMenuItem);

        menuBar.add(messengerMenu);

        chatMenu.setText("Edit");

        createChatMenuItem.setLabel("Open New Chat Room");
        chatMenu.add(createChatMenuItem);
        chatMenu.add(jSeparator1);

        menuBar.add(chatMenu);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(400, 699));
        setPreferredSize(new java.awt.Dimension(400, 670));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(signupPanel, "signup");
        getContentPane().add(contactsPanel, "contacts");
        getContentPane().add(loginPanel, "login");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        clientSocketConnection.sendClose();
    }//GEN-LAST:event_formWindowClosing

    public void switchView(String view) {
        layout.show(getContentPane(), view);
    }

    public ClientSocketConnection getClientSocketConnection() {
        return clientSocketConnection;
    }

    public SignupPanel getSignupPanel() {
        return signupPanel;
    }

    public loginPanel getLoginPanel() {
        return loginPanel;
    }

    public ContactsPanel getContactsPanel() {
        return contactsPanel;
    }

    public void viewNotification(String text) {
        notification.setContent(text);
        NotificationManager.showNotification(notification);
    }

    public void viewMenuBar() {
        this.setJMenuBar(menuBar);
    }

    public void hideMenuBar() {
        this.setJMenuBar(menuBar);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu chatMenu;
    private client.panels.ContactsPanel contactsPanel;
    private javax.swing.JMenuItem createChatMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private client.panels.loginPanel loginPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu messengerMenu;
    private javax.swing.JMenuItem signoutMenuItem;
    private client.panels.SignupPanel signupPanel;
    // End of variables declaration//GEN-END:variables
}
