package client;

import db.UserAccount;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.Vector;
import message.LoginToken;
import message.NetMessage;

/**
 *
 * @author mahmoud_fouad
 */
public class ClientSocketConnection extends Thread {

    private Socket socket;
    private ObjectInputStream dis;
    private ObjectOutputStream dos;
    private ChatApplication chatApplication;
    private UserAccount account;

    public ClientSocketConnection(ChatApplication chatApplication) {
        try {
            this.chatApplication = chatApplication;

            socket = new Socket(InetAddress.getLocalHost(), 10000);
            dos = new ObjectOutputStream(socket.getOutputStream());
            dos.flush();
            dis = new ObjectInputStream(socket.getInputStream());

            start();
        } catch (IOException ex) {
            ex.printStackTrace();
            //login.startWait();
            //login.setErrorText("Connection lost");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = dis.readObject();

                if (obj instanceof NetMessage) {
                    NetMessage netm = (NetMessage) obj;
                    switch (netm.getType()) {
                        case AUTH_OK:
                            account = (UserAccount) netm.getData();
                            chatApplication.getContactsPanel().setUserFullName(account.getFirstName() + " " + account.getLastName());
                            chatApplication.getContactsPanel().startTimer();//timer to update local contact list
                            chatApplication.getContactsPanel().createDefalutChatRoom();
                            chatApplication.switchView("contacts");

                            break;
                        case AUTH_FAIL:
                            chatApplication.getLoginPanel().setErrorText("wrong user name / password");
                            chatApplication.getLoginPanel().stopWaiting();
                            break;
                        case CONTACT_LIST:
                            chatApplication.getContactsPanel().getAllContacts().addAll((Collection<? extends UserAccount>) netm.getData());
                            chatApplication.getContactsPanel().updateContactList();
                            break;
                        case MESSAGE: {
                            Vector<Integer> receiver = netm.getReceiver();

                            if (receiver == null) { //defalut chat room
                                chatApplication.getContactsPanel().getDefalutChat().appenMessage((String) netm.getData());
                            } else {
                                receiver.remove(account.getId());
                                chatApplication.getContactsPanel().switchMessage((String) netm.getData(), receiver);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void sendLogin(String username, String password) {
        sendMessage(new NetMessage(NetMessage.NetMessageType.LOGIN, new LoginToken(username, password)));
    }

    synchronized public void sendMessage(NetMessage msg) {
        try {
            dos.writeObject(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendChangeState(int state) {
        sendMessage(new NetMessage(NetMessage.NetMessageType.STATE_CHANGE, state));
    }

    public void sendGetContactList() {
        sendMessage(new NetMessage(NetMessage.NetMessageType.CONTACT_LIST));
    }

    public void sendClose() {
        try {
            if (socket != null) {
                if (socket.isConnected()) {
                    dos.writeObject(new NetMessage(NetMessage.NetMessageType.DISCONNECT));
                    dos.flush();
                    this.stop();

                    dos.close();
                    dis.close();
                    socket.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
