import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

class Sender {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(2001);
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            DatagramPacket p = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getLocalHost(), 2002);
            s.send(p);
            s.close();
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}