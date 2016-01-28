package client.list;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.AbstractListModel;

/**
 *
 * @author mahmoud_fouad
 */
public class ContactsListDataModel extends AbstractListModel<Object> {

    private ArrayList<Object> dataList;

    public ContactsListDataModel() {
        dataList = new ArrayList<>();
    }

    public ContactsListDataModel(ArrayList<Object> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getSize() {
        return dataList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return dataList.get(index);
    }

    public void addToList(Object obj) {
        dataList.add(obj);
    }

    public void removeFromList(Object account) {
        dataList.remove(account);
    }

    public void removeFromList(int idx) {
        dataList.remove(idx);
    }

    public void addAll(Collection<Object> list) {
        dataList.addAll(list);
    }

    public void clear() {
        dataList.clear();
    }

    public void setDataList(ArrayList<Object> dataList) {
        this.dataList = dataList;
    }

    public void reload() {
        fireContentsChanged(this, 0, dataList.size() - 1);
    }

    public ArrayList<Object> getDataList() {
        return dataList;
    }
}
