import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Server {
    public static void main(String args[]) {
        ServerSocket ss = null;
        Scanner scan = new Scanner(System.in);
        try {
            ss = new ServerSocket(2000);
            Socket s = ss.accept();
            OutputStream out = s.getOutputStream();
            String msg = new String();
            while (true) {
                System.out.println("Enter message to send: ");
                msg = scan.nextLine();
                out.write(msg.getBytes());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        scan.close();
    }
}