package com.sockets;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {
        int port = 5555;
        int maxConnectionsCount = 50;
        ArrayList<ClientHandlerThread> connections = new ArrayList<>(maxConnectionsCount);
        try {
            System.out.println("Server is running");
            ServerSocket serverSocket = new ServerSocket(port);
            for (int i = 0; i < maxConnectionsCount; i++) {
                Socket socket = serverSocket.accept();  // awaits for a connection with client
                ClientHandlerThread clientHandler = new ClientHandlerThread(socket, line-> connections.forEach(s->s.writeToClient(line)));
                connections.add(clientHandler);
                new Thread(clientHandler).start();
            }
        }
        catch (IOException x) {  x.printStackTrace();}

    }
}

