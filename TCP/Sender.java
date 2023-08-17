import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

class Controller {
    ServerSocket ss;
    Socket s;
    Controller () {
        try {
            ss = new ServerSocket(2000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void acceptConn () {
        try {
            s = ss.accept();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    boolean sendMessage(String msg) {
        try {
            OutputStream out = s.getOutputStream();
            out.write(msg.getBytes());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}

class Sender {
    JFrame f;
    Controller ctrl;
    Sender() {
        f = new JFrame("Sender");
        ctrl = new Controller();

        JLabel msglabel = new JLabel("Message: ");
        msglabel.setBounds(20, 20, 200, 20);
        f.add(msglabel);

        JTextField msg = new JTextField();
        msg.setBounds(20, 40, 200, 20);
        f.add(msg);

        JButton send = new JButton("Send");
        send.setBounds(20, 70, 200, 20);
        f.add(send);

        JLabel resp = new JLabel();
        resp.setBounds(20, 100, 200, 20);
        f.add(resp);

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrl.acceptConn();
                ctrl.sendMessage(msg.getText());
            }
        });

        f.setSize(250, 250);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Sender();
    }
}
