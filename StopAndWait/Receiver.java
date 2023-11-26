import java.net.*;
import java.io.*;

class Receiver {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 2001);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps = new PrintStream(s.getOutputStream());

            while (true) {
                String msg = br.readLine();
                System.out.println("Message received: "+msg);
                ps.println("ACK");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}