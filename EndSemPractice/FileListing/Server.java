import java.net.*;
import java.io.*;
import java.util.*;

class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(2001);
            int id = 0;
            while (true) {
                Socket s = ss.accept();
                ServerThread st = new ServerThread(s, id++);
                st.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ServerThread extends Thread {
    Socket s;
    PrintStream ps;
    BufferedReader br;
    int id;
    ServerThread(Socket s, int id) {
        this.s = s;
        this.id = id;
        try {
            ps = new PrintStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void run() {
        try {
            System.out.println("Enter folder path for machine "+id+": ");
            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine();
            ps.println(path);
            String msg = br.readLine();
            System.out.println(id+": "+msg);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
