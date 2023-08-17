import java.io.InputStream;
import java.net.Socket;

class Client {
    public static void main(String args[]) {
        try {
            Socket s = new Socket("localhost",2000);
            InputStream in = s.getInputStream();
            byte b[] = new byte[10];
            while (true) {
                b = "          ".getBytes();
                in.read(b,0,10);
                System.out.println(new String(b));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
