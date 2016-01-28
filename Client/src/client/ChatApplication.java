package client;

import client.panels.ContactsPanel;
import client.panels.SignupPanel;
import client.panels.loginPanel;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author mahmoud_fouad
 */
public class ChatApplication extends javax.swing.JFrame {

    private CardLayout layout;
    private ClientSocketConnection clientSocketConnection;

    public ChatApplication() {
        clientSocketConnection = new ClientSocketConnection(this);

        initComponents();

        layout = (CardLayout) getContentPane().getLayout();

        switchView("login");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        signupPanel = new SignupPanel(this)
        ;
        contactsPanel = new ContactsPanel(this)
        ;
        loginPanel = new loginPanel(this)
        ;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private client.panels.ContactsPanel contactsPanel;
    private client.panels.loginPanel loginPanel;
    private client.panels.SignupPanel signupPanel;
    // End of variables declaration//GEN-END:variables
}
