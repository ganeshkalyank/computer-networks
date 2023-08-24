import java.net.*;

class TestSender {
  public static void main(String args[]) {
    try {
      DatagramSocket ds = new DatagramSocket(3000);
      DatagramPacket dp = new DatagramPacket("welcome".getBytes(),7,InetAddress.getByName("localhost"),4000);
      ds.send(dp);
      System.out.println("Successfully sent...");
      ds.close();
    } catch (Exception e) {}
  }
}
