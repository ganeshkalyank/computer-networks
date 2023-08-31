import java.io.*;
import java.net.*;

class TestClient {
  public static void main(String args[]) {
    BufferedReader br1=null, br2=null;
    PrintStream p = null;
    Socket s = null;
    try {
      s = new Socket("localhost",2001);
      System.out.println("Connection established!");
      br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
      br2 = new BufferedReader(new InputStreamReader(System.in));
      p = new PrintStream(s.getOutputStream());

      System.out.println("Enter message to server:");
      String to_server = br2.readLine();
      String from_server = null;

      while(to_server.compareTo("Exit")!=0) {
        p.println(to_server);
        p.flush();
        from_server = br1.readLine();
        System.out.println("Msg received from server: "+from_server);
        System.out.println("Enter message to server:");
        to_server = br2.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}