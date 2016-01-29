package handlers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mahmoud_fouad
 */
public class ServerSocketHandler extends Thread {

    private ServerSocket serverSocket;
    private boolean accept = false;

    public ServerSocketHandler() {

    }

    @Override
    public void run() {
        Socket socket = null;

        while (true) {
            if (accept) {
                try {
                    if (serverSocket == null) {
                        try {
                            serverSocket = new ServerSocket(10000);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            serverSocket = null;
                            continue;
                        }
                    }

                    socket = serverSocket.accept();
                    new ClientHandler(socket);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    // ex.printStackTrace();
                }
            }
        }

    }

    public void stopServerConnection() {
        accept = false;
        ClientHandler.sendDisconnectToAll();
        try {
            serverSocket.close();
            serverSocket = null;
        } catch (IOException ex) {
            serverSocket = null;
            //System.exit(-1);
        }
    }

    public void startServerConnection() {
        accept = true;
    }

}

/*

 Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                ClientHandler.broadcastClose();
            }

        });


 */
