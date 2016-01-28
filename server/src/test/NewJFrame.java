package test;

import db.UserAccount;
import handlers.DBHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author mahmoud_fouad
 */
public class NewJFrame extends javax.swing.JFrame {

    private ContactsListDataModel contactsListDataModel;

    private class ContactsDataHandler extends Thread {

        private DBHandler dBHandler;
        Vector<Object> ava = new Vector<>();
        Vector<Object> bus = new Vector<>();
        Vector<Object> unav = new Vector<>();

        public ContactsDataHandler() {

            try {
                dBHandler = new DBHandler();
            } catch (SQLException ex) {
                ex.printStackTrace();
                dBHandler = null;
            }

        }

        @Override
        public void run() {
            if (dBHandler != null) {
                while (true) {
                    ava.clear();
                    bus.clear();
                    unav.clear();

                    ArrayList<UserAccount> allContacts = dBHandler.getAllContacts();//edit that

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
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

    }

    public NewJFrame() {
        ContactsDataHandler dataHandler = new ContactsDataHandler();
        contactsListDataModel = new ContactsListDataModel();

        initComponents();

        dataHandler.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(contactsListDataModel);
        jList1.setCellRenderer(new ContactsListCellRender());
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Object> jList1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
