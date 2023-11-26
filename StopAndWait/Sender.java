import java.net.*;
import java.io.*;

class Sender {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(2001);
            Socket s = ss.accept();
            s.setSoTimeout(1000);
            PrintStream ps = new PrintStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String[] messages = {"Message One","Message Two","Message Three","Message Four"};
            int counter = 0;
            while (true) {
                ps.println(messages[counter]);
                String ack;
                try {
                    ack = br.readLine();
                } catch (Exception e) {
                    System.out.println("Timeout");
                    continue;
                }
                if (ack.equals("ACK")) {
                    counter = (counter+1)%4;
                } else if (ack.equals("NACK")) {
                    continue;
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}