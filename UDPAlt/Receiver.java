import java.net.DatagramPacket;
import java.net.DatagramSocket;

class Receiver {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(2002);
            byte[] bytes = new byte[128];
            DatagramPacket p = new DatagramPacket(bytes, 128);
            s.receive(p);
            String msg = new String(bytes);
            System.out.println(msg);
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}