package server.list;

import db.UserAccount;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

/**
 *
 * @author mahmoud_fouad
 */
public class ServerListCellRender extends JPanel implements ListCellRenderer<UserAccount> {

    private JLabel idxLabel = new JLabel();
    private JLabel valueLabel = new JLabel();
    private Border LineBorder = BorderFactory.createLineBorder(Color.BLUE, 3, true);

    public ServerListCellRender() {
        add(idxLabel);
        add(valueLabel);

        idxLabel.setBackground(Color.WHITE);
        valueLabel.setBackground(Color.WHITE);

        FlowLayout flow = (FlowLayout) getLayout();
        flow.setAlignment(FlowLayout.LEFT);
        flow.setHgap(10);
        flow.setVgap(5);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends UserAccount> list, UserAccount account, int index, boolean isSelected, boolean cellHasFocus) {
        idxLabel.setText((index + 1) + " -");

        valueLabel.setText(account.getFirstName() + " " + account.getLastName());
        valueLabel.setIcon(new ImageIcon(getClass().getResource("/resources/" + account.getStatus() + ".PNG")));

        if (isSelected) {
            this.setBackground(Color.GRAY);
            this.setBorder(LineBorder);
        } else {
            this.setBackground(Color.WHITE);
            this.setBorder(null);
        }

        return this;
    }

}
