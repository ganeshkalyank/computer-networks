import java.io.*;
import java.net.*;

class TestServer {
  public static void main(String args[]) {
    ServerSocket ss = null;
    Socket s = null;
    try {
      ss = new ServerSocket(2001);

      System.out.println("Localhost IP: "+InetAddress.getLocalHost());
      System.out.println("Server listening");

      while(true) {
        s = ss.accept();
        System.out.println("Connection established!");
        ServerThread st = new ServerThread(s);
        st.start();
      }
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
}

class ServerThread extends Thread {
    BufferedReader br;
    PrintStream p = null;
    Socket s = null;
    ServerThread(Socket k) {
      s = k;
    }
    public void run() {
      try {
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        p = new PrintStream(s.getOutputStream());
        String r_msg = br.readLine();
        while (r_msg.compareTo("Exit")!=0) {
          p.println(r_msg);
          p.flush();
          System.out.println("Message received from client: "+r_msg);
          r_msg = br.readLine();
        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }
}