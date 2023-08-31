import java.io.*;
import java.net.*;

class Server {
    public static void main(String args[]) {
        ServerSocket ss;
        Socket s;
        try {
            ss = new ServerSocket(2001);
            System.out.println("Waiting for connections...");
            while (true) {
                s = ss.accept();
                System.out.println("Connection established!");
                ServerThread st = new ServerThread(s);
                st.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ServerThread extends Thread {
    Socket s;
    BufferedReader br;
    PrintStream ps;
    ServerThread(Socket k) {
        s = k;
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ps = new PrintStream(s.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void run() {
        try {
            while (true) {
                String msg = br.readLine();
                while (msg.compareTo("exit") != 0) {
                    System.out.println("Message received from client: "+msg);
                    ps.println(msg);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}