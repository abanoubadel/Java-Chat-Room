package client.list;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import db.UserAccount;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author mahmoud_fouad
 */
public class ContactsListDataModel extends AbstractListModel<Object> {

    private ArrayList<Object> available;
    private ArrayList<Object> busy;
    private ArrayList<Object> unavailable;

    private Iterable<Object> data;
    private List dataList;

    public ContactsListDataModel() {
    }

    @Override
    public int getSize() {
        if (available == null
                || busy == null
                || unavailable == null) {
            return 0;
        }

        return dataList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return dataList.get(index);
    }

    public void updateState(Object obj, int oldState) {
        if (obj instanceof UserAccount) {
            switch (oldState) {
                case UserAccount.AVAILABLE:
                    available.remove(obj);
                    break;
                case UserAccount.BUSY:
                    busy.remove(obj);
                    break;
                default:
                    unavailable.remove(obj);
            }

            UserAccount account = (UserAccount) obj;

            switch (account.getStatus()) {
                case UserAccount.AVAILABLE:
                    available.add(account);
                    break;
                case UserAccount.BUSY:
                    busy.add(account);
                    break;
                default:
                    unavailable.add(account);
            }

            dataList = Lists.newArrayList(data);
            fireContentsChanged(this, 0, dataList.size() - 1);
        }

    }

    public void initializeDataModel(ArrayList<UserAccount> allContacts) {
        available = new ArrayList<>();
        busy = new ArrayList<>();
        unavailable = new ArrayList<>();

        available.add("    available");
        busy.add("    busy");
        unavailable.add("    available");

        for (int i = 0; i < allContacts.size(); i++) {
            UserAccount con = allContacts.get(i);

            switch (con.getStatus()) {
                case UserAccount.AVAILABLE:
                    available.add(con);
                    break;
                case UserAccount.BUSY:
                    busy.add(con);
                    break;
                default:
                    unavailable.add(con);
            }
        }

        data = Iterables.concat(available, busy, unavailable);
        dataList = Lists.newArrayList(data);

        fireContentsChanged(this, 0, dataList.size() - 1);
    }

    public void removeFromList(Object account) {
        dataList.remove(account);
    }

    public void removeFromList(int idx) {
        dataList.remove(idx);
    }

    public void clear() {
        dataList.clear();
    }

    public void reload() {
        fireContentsChanged(this, 0, dataList.size() - 1);
    }

}
