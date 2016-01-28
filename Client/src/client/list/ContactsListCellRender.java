package client.list;

import db.UserAccount;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class ContactsListCellRender extends javax.swing.JPanel implements ListCellRenderer<Object> {

    private JLabel textLabe = new JLabel();
    private final Border border = BorderFactory.createLineBorder(Color.GRAY, 5, true);

    public ContactsListCellRender() {
        initComponents();

        textLabe = new JLabel();
        textLabe.setSize(this.getWidth(), 60);
        textLabe.setFont(new Font("Cantarell", Font.BOLD, 23));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/2-1.PNG"))); // NOI18N
        imageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/gray.PNG"))); // NOI18N
        statusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        nameLabel.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        nameLabel.setText("Full Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(imageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean selected, boolean cellHasFocus) {
        JComponent returnValue = null;
       
        if (value instanceof UserAccount) {
            UserAccount account = (UserAccount) value;
            nameLabel.setText(account.getFirstName() + " " + account.getLastName());

            ImageIcon imageIcon = null;

            switch (account.getStatus()) {
                case UserAccount.AVAILABLE:
                    imageIcon = new ImageIcon(getClass().getResource("/resources/ava.PNG"));
                    break;
                case UserAccount.BUSY:
                    imageIcon = new ImageIcon(getClass().getResource("/resources/red.PNG"));
                    break;
                default:
                    imageIcon = new ImageIcon(getClass().getResource("/resources/gray.PNG"));
            }

            statusLabel.setIcon(imageIcon);

            if (selected) {
                setBorder(border);
            } else {
                setBorder(null);
            }

            returnValue = this;
        } else {
            textLabe.setText(value.toString());
            returnValue = textLabe;
        }

        return returnValue;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}
