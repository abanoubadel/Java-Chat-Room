package client;

import db.UserAccount;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
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
        this.chatApplication = chatApplication;

        start();
    }

    @Override
    public void run() {
        while (true) {
            //try to connect
            try {

                socket = new Socket(InetAddress.getLocalHost(), 10000);
                dos = new ObjectOutputStream(socket.getOutputStream());
                dos.flush();
                dis = new ObjectInputStream(socket.getInputStream());

                //connection done
                while (true) {
                    Object obj = dis.readObject();

                    if (obj instanceof NetMessage) {
                        NetMessage netm = (NetMessage) obj;
                        switch (netm.getType()) {
                            case AUTH_OK:
                                account = (UserAccount) netm.getData();
                                String name = account.getFirstName() + " " + account.getLastName();
                                chatApplication.getContactsPanel().setUserFullName(name);
                                chatApplication.getContactsPanel().getInitContacts();
                                chatApplication.getContactsPanel().createDefalutChatRoom();
                                chatApplication.viewNotification("Welcom " + name + " \nHave a nice Day :D");
                                chatApplication.viewMenuBar();
                                chatApplication.switchView("contacts");
                                break;
                            case AUTH_FAIL:
                                chatApplication.getLoginPanel().setErrorText("wrong user name / password");
                                chatApplication.getLoginPanel().stopWaiting();
                                break;
                            case CONTACT_LIST:
                                chatApplication.getContactsPanel().getContactsListDataModel().initializeDataModel((ArrayList<UserAccount>) netm.getData());
                                break;
                            case UPDATE_CONTACT_LIST:
                                chatApplication.getContactsPanel().getContactsListDataModel().updateState(netm.getData(), (int) netm.getAttribute());

                                UserAccount tempAccount = (UserAccount) netm.getData();
                                String state = "";
                                switch (tempAccount.getStatus()) {
                                    case UserAccount.AVAILABLE:
                                        state = "Available";
                                        break;
                                    case UserAccount.BUSY:
                                        state = "Busy";
                                        break;
                                    default:
                                        state = "unavailable";
                                }
                                chatApplication.viewNotification(tempAccount.getFirstName() + " " + tempAccount.getLastName() + " just got " + state);
                                break;
                            case DISCONNECT:
                                //close opened chats
                                chatApplication.getContactsPanel().distroyAllChatRoom();
                                chatApplication.getLoginPanel().stopWaiting();
                                chatApplication.hideMenuBar();
                                chatApplication.switchView("login");
                                throw new IOException("restart connection");
                            case MESSAGE: {
                                Vector<Integer> receiver = netm.getReceiver();

                                if (receiver == null) { //defalut chat room
                                    chatApplication.getContactsPanel().getDefalutChat().appenMessage((String) netm.getData());
                                } else {
                                    receiver.remove((Integer) account.getId());
                                    chatApplication.getContactsPanel().switchMessage((String) netm.getData(), receiver);
                                }

                                UserAccount tempSender = (UserAccount) netm.getAttribute();
                                chatApplication.viewNotification(tempSender.getFirstName() + " " + tempSender.getLastName() + " : " + netm.getData());

                            }
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("wait while and retry");
                socket = null;

                try {
                    Thread.sleep(600);
                } catch (InterruptedException exc) {
                    // ex.printStackTrace();
                }
            } catch (ClassNotFoundException ex) {
                //must not happen
                ex.printStackTrace();
            }
        }

    }

    public void sendLogin(String username, String password) {
        if (socket != null) {
            sendMessage(new NetMessage(NetMessage.NetMessageType.LOGIN, new LoginToken(username, password)));
        } else {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    chatApplication.getLoginPanel().setErrorText("unable to connect");
                    chatApplication.getLoginPanel().stopWaiting();
                }
            });
//            chatApplication.getLoginPanel().setErrorText("unable to connect");
//            chatApplication.getLoginPanel().stopWaiting();
        }
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
