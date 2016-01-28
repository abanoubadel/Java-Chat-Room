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

    public ServerSocketHandler() {
        try {
            serverSocket = new ServerSocket(10000);
        } catch (IOException ex) {
            ex.printStackTrace();
            serverSocket = null;
        }
    }

    @Override
    public void run() {
        if (serverSocket != null) {
            Socket socket = null;

            while (true) {
                try {
                    socket = serverSocket.accept();
                    new ClientHandler(socket);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
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
