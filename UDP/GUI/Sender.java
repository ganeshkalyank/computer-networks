import java.awt.event.*;
import javax.swing.*;
import java.net.*;

class Sender {
  JFrame f;
  DatagramSocket ds;
  Sender() {
    f = new JFrame("Sender-UDP");
    
    JLabel tolabel = new JLabel("To");
    tolabel.setBounds(20,20,100,20);
    f.add(tolabel);

    JTextField to = new JTextField();
    to.setBounds(20,40,100,20);
    f.add(to);

    JLabel portlabel = new JLabel("Port");
    portlabel.setBounds(130,20,90,20);
    f.add(portlabel);

    JTextField port = new JTextField();
    port.setBounds(130,40,90,20);
    f.add(port);

    JLabel msglabel = new JLabel("Message");
    msglabel.setBounds(20,70,200,20);
    f.add(msglabel);

    JTextField msg = new JTextField();
    msg.setBounds(20,90,200,20);
    f.add(msg);

    JButton send = new JButton("Send");
    send.setBounds(20,120,200,20);
    f.add(send);

    JLabel resp = new JLabel();
    resp.setBounds(20,150,200,20);
    f.add(resp);

    JLabel loglabel = new JLabel("Message Log");
    loglabel.setBounds(250,20,200,20);
    f.add(loglabel);

    JTextArea log = new JTextArea();
    log.setBounds(250,40,200,170);
    log.setEditable(false);
    f.add(log);

    try {
      ds = new DatagramSocket(3000);
    } catch (Exception e) {
      resp.setText(e.toString());
    }

    send.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (to.getText().equals("")) throw new Exception("Invalid target host!");
          String msgToSend = msg.getText();
          if (msgToSend.equals("")) throw new Exception("Message is empty!");
          DatagramPacket dp = new DatagramPacket(msgToSend.getBytes(),msgToSend.length(),InetAddress.getByName(to.getText()),Integer.parseInt(port.getText()));
          ds.send(dp);
          log.append("To "+to.getText()+": "+msgToSend+"\n");
          resp.setText("Message sent successfully!");
        } catch (Exception ex) {
          resp.setText(ex.getMessage());
        }
      }
    });

    f.setLayout(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(500,300);
  }
  public static void main(String args[]) {
    new Sender();
  }
}