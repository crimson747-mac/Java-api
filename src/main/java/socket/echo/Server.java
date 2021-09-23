package socket.echo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server ready");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true) {
            try{
                Socket socket = serverSocket.accept();
                System.out.println("client is connected");

                InputStream is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                String message = dis.readUTF();

                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF("[ECHO] : " + message);

                dis.close();
                dos.close();
                socket.close();
                System.out.println("client connection is closed");
            } catch (Exception e) {

            }
        }
    }
}
