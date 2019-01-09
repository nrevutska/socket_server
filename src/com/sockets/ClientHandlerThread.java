package com.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;


public class ClientHandlerThread implements Runnable {
    private Socket socket;
    private Consumer<String> onMessageReceive;
    private DataOutputStream outputStream;

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            String line;
            while (true) {
                line = inputStream.readUTF();
                System.out.println("received: " + line);
                if (onMessageReceive != null)
                    onMessageReceive.accept(line);
//                outputStream.writeUTF(line);
//                outputStream.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void writeToClient(String str) {
        try {
            outputStream.writeUTF(str);
            outputStream.flush();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    protected ClientHandlerThread(Socket socket, Consumer<String> onMessageReceive) {
        this.socket = socket;
        this.onMessageReceive = onMessageReceive;
    }

//    public interface clientHandlerDelegate {
//        void execute(String line);
//    }

}
