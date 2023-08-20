import javax.swing.*;
import java.net.*;

class Receiver {
  JFrame f;
  DatagramSocket ds;
  DatagramPacket dp;
  Receiver() {
    f = new JFrame("UDP-Receiver");

    JLabel loglabel = new JLabel("Message Log");
    loglabel.setBounds(20,20,200,20);
    f.add(loglabel);

    JTextArea log = new JTextArea();
    log.setBounds(20,40,200,200);
    log.setEditable(false);
    f.add(log);

    f.setLayout(null);
    f.setSize(300,300);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    try {
      ds = new DatagramSocket(4000);
      byte b[] = new byte[20];
      dp = new DatagramPacket(b,0,20);
      while (true) {
        dp.setData("                    ".getBytes());
        ds.receive(dp);
        log.append("From " + dp.getAddress() + ": " + new String(dp.getData()) + "\n");
      }
    } catch (Exception e) {
      log.append(e.getMessage()+"\n");
    }
  }
  public static void main(String args[]) {
    new Receiver();
  }
}