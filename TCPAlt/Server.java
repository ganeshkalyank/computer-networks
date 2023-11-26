import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Server {
    public static void main(String[] args) {
        ServerSocket ss;
        Scanner scanner = new Scanner(System.in);
        try {
            ss = new ServerSocket(2001);
            Socket s = ss.accept();
            PrintStream ps = new PrintStream(s.getOutputStream());
            while (true) {
                String msg = scanner.nextLine();
                String msgToSend = "";
                for (int i=0;i<msg.length();i++) {
                    msgToSend += (char) ((int)(msg.charAt(i)) + 3);
                }
                ps.println(msgToSend);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        scanner.close();
    }
}