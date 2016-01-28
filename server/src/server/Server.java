package server;

import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import server.list.ServerListDataModel;
import db.UserAccount;
import handlers.DBHandler;
import handlers.ServerSocketHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import server.list.ServerListCellRender;

/**
 *
 * @author mahmoud_fouad
 */
public class Server extends javax.swing.JFrame {

    private ServerListDataModel listDataModel;

    public Server() {

        initComponents();

        new DataThread().start();

    }

    private class DataThread extends Thread {

        DBHandler handler;

        public DataThread() {
            try {
                handler = new DBHandler();
            } catch (SQLException ex) {
                handler = null;
            }
        }

        @Override
        public void run() {
            if (handler != null) {
                while (true) {
                    ArrayList<UserAccount> accounts = handler.getAllContacts();
                    listDataModel.setDataList(accounts);

                    int[] vals = new int[4];
                    for (UserAccount account : accounts) {
                        vals[account.getStatus()]++;
                    }

                    userCountLabel.setText("" + accounts.size());
                    busyCountLabel.setText("" + vals[UserAccount.BUSY]);
                    unavilableCountLabel.setText("" + vals[UserAccount.UNAVILABLE]);
                    connetedCountLabel.setText("" + vals[UserAccount.AVAILABLE]);
                    hiddenCountLabel.setText("" + vals[UserAccount.HIDDEN]);
                    // usersList.repaint(10);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList<UserAccount>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userCountLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        busyCountLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        hiddenCountLabel = new javax.swing.JLabel();
        dummyLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        unavilableCountLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        connetedCountLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1065, 572));
        setPreferredSize(new java.awt.Dimension(1065, 572));

        listDataModel = new ServerListDataModel();
        usersList.setModel(listDataModel);
        usersList.setCellRenderer(new ServerListCellRender());
        jScrollPane1.setViewportView(usersList);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(2, 6));

        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Users Count :");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);

        userCountLabel.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        userCountLabel.setForeground(new java.awt.Color(215, 38, 17));
        userCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userCountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(userCountLabel);

        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Currently Busy : ");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel4);

        busyCountLabel.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        busyCountLabel.setForeground(new java.awt.Color(215, 38, 17));
        busyCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        busyCountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(busyCountLabel);

        jLabel5.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Currently Hidden : ");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel5);

        hiddenCountLabel.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        hiddenCountLabel.setForeground(new java.awt.Color(215, 38, 17));
        hiddenCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hiddenCountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(hiddenCountLabel);
        jPanel1.add(dummyLabel);

        jLabel3.setFont(new java.awt.Font("Cantarell", 0, 17)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Currently Unavilable : ");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel3);

        unavilableCountLabel.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        unavilableCountLabel.setForeground(new java.awt.Color(215, 38, 17));
        unavilableCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unavilableCountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(unavilableCountLabel);

        jLabel2.setFont(new java.awt.Font("Cantarell", 0, 17)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Currently Connected : ");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel2);

        connetedCountLabel.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        connetedCountLabel.setForeground(new java.awt.Color(215, 38, 17));
        connetedCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connetedCountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(connetedCountLabel);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Description", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel2.setLayout(new java.awt.GridLayout(4, 1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/2.PNG"))); // NOI18N
        jLabel6.setText("Available");
        jPanel2.add(jLabel6);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/0.PNG"))); // NOI18N
        jLabel7.setText("Unavailable");
        jPanel2.add(jLabel7);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/1.PNG"))); // NOI18N
        jLabel8.setText("Busy");
        jPanel2.add(jLabel8);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/3.PNG"))); // NOI18N
        jLabel9.setText("Hidden");
        jPanel2.add(jLabel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        Server s = new Server();
        ServerSocketHandler handler = new ServerSocketHandler();
        handler.start();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                s.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel busyCountLabel;
    private javax.swing.JLabel connetedCountLabel;
    private javax.swing.JLabel dummyLabel;
    private javax.swing.JLabel hiddenCountLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel unavilableCountLabel;
    private javax.swing.JLabel userCountLabel;
    private javax.swing.JList<UserAccount> usersList;
    // End of variables declaration//GEN-END:variables
}
