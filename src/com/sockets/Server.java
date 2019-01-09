package com.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int port = 5555;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();  // awaits for a connection with client
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            String line;
            while (true) {
                line = inputStream.readUTF();
                System.out.println("received" + line);
                outputStream.writeUTF(line);
                outputStream.flush();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
