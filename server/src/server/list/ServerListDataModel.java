package server.list;

import db.UserAccount;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author mahmoud_fouad
 */
public class ServerListDataModel extends AbstractListModel<UserAccount> {

    private ArrayList<UserAccount> dataList;

    public ServerListDataModel() {
        dataList = new ArrayList<>();
    }

    public ServerListDataModel(ArrayList<UserAccount> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getSize() {
        return dataList.size();
    }

    @Override
    public UserAccount getElementAt(int index) {
        return dataList.get(index);
    }

    public void addToList(UserAccount account) {
        dataList.add(account);
        fireContentsChanged(this, 0, dataList.size() - 1);
    }

    public void removeFromList(UserAccount account) {
        dataList.remove(account);
    }

    public void removeFromList(int idx) {
        dataList.remove(idx);
    }

    public void setDataList(ArrayList<UserAccount> dataList) {
        this.dataList = dataList;
        fireContentsChanged(this, 0, dataList.size() - 1);
    }

    public ArrayList<UserAccount> getDataList() {
        return dataList;
    }
}
