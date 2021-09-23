package socket.echo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            System.out.println("Connection Success");

            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();

            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(message);

            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            System.out.println(dis.readUTF());

            dos.close();
            dis.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
