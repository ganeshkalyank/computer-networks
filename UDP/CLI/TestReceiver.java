import java.net.*;

class TestReceiver {
  public static void main(String args[]) {
    try {
      DatagramSocket ds = new DatagramSocket(4000);
      byte b[] = new byte[7];
      DatagramPacket dp = new DatagramPacket(b,0,7);
      ds.receive(dp);
      System.out.println(new String(dp.getData()));
      ds.close();
    } catch (Exception e) {}
  }
}