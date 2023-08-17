import java.net.*;
import java.io.*;

class Receiver {
    public static void main(String args[]) {
        try {
            Socket s = new Socket("localhost",2000);
            InputStream in = s.getInputStream();
            byte b[] = new byte[10];
            in.read(b);
            System.out.println(new String(b));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}