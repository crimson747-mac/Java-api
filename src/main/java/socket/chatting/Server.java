package socket.chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Server {
    HashMap clients;

    Server() {
        clients = new HashMap();
        Collections.synchronizedMap(clients);
    }

    public void start() {
        ServerSocket serverSocket = null; Socket socket = null;
        try{
            serverSocket = new ServerSocket(9999); System.out.println("start server...");

            while(true) {
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress()+":"+ socket.getPort()+" connect!");
                ServerReceiver thread = new ServerReceiver(socket);
                thread.start();
            }//while
        }catch(Exception e) {e.printStackTrace();}
    }

    void sendToAll(String msg) { //브로드캐스팅 기능
        Iterator iterator = clients.keySet().iterator();
        while(iterator.hasNext()) {
            try {
                DataOutputStream out = (DataOutputStream) clients.get(iterator.next());
                out.writeUTF(msg);
            } catch(IOException e) {e.printStackTrace();}
        }//while
    }

    public static void main(String[] args) {
        new Server().start();
    }

    class ServerReceiver extends Thread {
        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        ServerReceiver(Socket socket) {
            this.socket = socket;
            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String name = "";
            try {
                name = in.readUTF();
                if (clients.get(name) != null) { //같은 이름사용자 존재
                    out.writeUTF("#Aleady exist name : " + name);
                    out.writeUTF("#Please reconnect by other name");
                    System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " disconnect!");

                    in.close();
                    out.close();
                    socket.close();

                    socket = null;
                } else { //같은 이름 존재하지 않는 경우
                    sendToAll("#"+name+" join!"); clients.put(name, out);
                    while (in != null) {
                        sendToAll(in.readUTF());
                    }
                }//else
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    sendToAll("#" + name + " exit!");
                    clients.remove(name);
                    System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " disconnect!");
                }
            }
        }
    }

}
