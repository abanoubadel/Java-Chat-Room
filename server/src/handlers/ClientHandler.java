package handlers;

import db.UserAccount;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import message.LoginToken;
import message.NetMessage;

/**
 *
 * @author mahmoud_fouad
 */
public class ClientHandler extends Thread {

    private ObjectInputStream din;
    private ObjectOutputStream dout;
    private Socket socket;

    private static Vector<ClientHandler> unauthUsers = new Vector<>();
    private UserAccount account;

    private static DBHandler handler;
    private static Hashtable<Integer, ClientHandler> clients = new Hashtable<>();

    static {
        try {
            handler = new DBHandler();
        } catch (SQLException ex) {
            ex.printStackTrace();
            handler = null;
        }
    }

    public ClientHandler(Socket socket) {

        try {
            this.socket = socket;

            dout = new ObjectOutputStream(socket.getOutputStream());
            dout.flush();
            din = new ObjectInputStream(socket.getInputStream());

            unauthUsers.add(this);

            this.start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = din.readObject();

                if (obj instanceof NetMessage) {
                    NetMessage netm = (NetMessage) obj;

                    switch (netm.getType()) {
                        case LOGIN:
                            validateLogin((LoginToken) netm.getData());
                            break;
                        case DISCONNECT:
                            logout();
                            return;
                        case MESSAGE: {
                            //diff types waittttt
                            Vector<Integer> receiverList = netm.getReceiver();
                            if (receiverList == null) {
                                broadcastMessageToAll(netm);
                            } else {
                                receiverList.add(account.getId());
                                broadcastMessageToSelected(netm);
                            }
                        }
                        break;
                        case CONTACT_LIST:
                            sendContactList();
                            break;
                        case CREATE:

                            break;
                        case STATE_CHANGE:
                            account.setStatus((int) netm.getData());
                            handler.updateUserStatus(account);
                            break;
                        case VALIDATE_EMAIL:
                            boolean emailVal = handler.validateMail((String) netm.getData());
                            sendMessage(new NetMessage(NetMessage.NetMessageType.EMAIL_VALIDATION_RESULT, emailVal));
                            sendDisconnect();
                            break;
                        case VALIDATE_USER_NAME:
                            boolean usernameVal = handler.validateUserName((String) netm.getData());
                            sendMessage(new NetMessage(NetMessage.NetMessageType.USER_NAME_VALIDATION_RESULT, usernameVal));
                            sendDisconnect();
                            break;
                    }

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void validateLogin(LoginToken token) {
        UserAccount loginValue = handler.login(token);

        if (loginValue != null) {
            loginValue.setStatus(UserAccount.AVAILABLE);
            handler.updateUserStatus(loginValue);

            sendMessage(new NetMessage(NetMessage.NetMessageType.AUTH_OK, loginValue));
            account = loginValue;

            unauthUsers.remove(this);
            clients.put(account.getId(), this);
        } else {
            sendMessage(new NetMessage(NetMessage.NetMessageType.AUTH_FAIL));
        }
    }

    private void sendMessage(NetMessage msg) {
        try {
            dout.writeObject(msg);
            dout.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void broadcastMessageToAll(NetMessage msg) {
        for (ClientHandler client : clients.values()) {
            client.sendMessage(msg);
        }

    }

    private void broadcastMessageToSelected(NetMessage msg) {
        for (Integer i : msg.getReceiver()) {
            clients.get(i).sendMessage(msg);
        }

    }

    private void logout() {
        account.setStatus(UserAccount.UNAVILABLE);
        handler.updateUserStatus(account);
        clients.remove(account.getId());
        //close socket + streams
        try {
            din.close();
            dout.close();
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void sendContactList() {
        ArrayList<UserAccount> contactList = handler.getContactList(account);
        sendMessage(new NetMessage(NetMessage.NetMessageType.CONTACT_LIST, contactList));
    }

    private void sendDisconnect() {
        sendMessage(new NetMessage(NetMessage.NetMessageType.DISCONNECT));
    }

    private void sendDisconnectToAll() {
        for (ClientHandler client : clients.values()) {
            client.sendMessage(new NetMessage(NetMessage.NetMessageType.DISCONNECT));
        }

        for (int i = 0; i < unauthUsers.size(); i++) {
            unauthUsers.elementAt(i).sendMessage(new NetMessage(NetMessage.NetMessageType.DISCONNECT));
        }
    }

}
