import java.io.*;
import java.net.*;
import java.util.*;

class Server {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket ss = new ServerSocket(2001);
            int id = 0;
            while (true) {
                Socket s = ss.accept();
                ServerThread st = new ServerThread(s, id);
                st.start();
                id++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ServerThread extends Thread {
    Socket s;
    int id;
    PrintStream ps;
    BufferedReader br;
    ServerThread(Socket s, int id) {
        this.s = s;
        this.id = id;
        try {
            ps = new PrintStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Connected with machine " + id + "...");
    }
    public void run() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter folder name for machine "+id+": ");
            String name = scan.nextLine();
            ps.println(name);
            System.out.println(id+": "+br.readLine());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}