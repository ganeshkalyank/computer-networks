import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

class Client {
    JFrame f;
    BufferedReader br;
    PrintStream ps;
    Socket s;
    JTextArea log;
    Client() {
        f = new JFrame("Client");
        f.setSize(300,300);

        log = new JTextArea();
        log.setBounds(20, 20, 240, 200);
        log.setEditable(false);
        f.add(log);

        JTextArea msg = new JTextArea();
        msg.setBounds(20, 230, 160, 20);
        f.add(msg);

        JButton send = new JButton("Send");
        send.setBounds(180, 230, 80, 20);
        f.add(send);

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                log.append("Me: "+msg.getText()+"\n");
                try {
                    ps.println(msg.getText());
                } catch (Exception ex) {
                    System.out.println(e);
                }
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        Client c = new Client();
        try {
            c.s = new Socket("localhost", 2001);
            c.ps = new PrintStream(c.s.getOutputStream());
            c.br = new BufferedReader(new InputStreamReader(c.s.getInputStream()));
        } catch (Exception e) {
            System.out.println(e);
        }
        while (true) {
            try {
                c.log.append(c.s.getInetAddress()+": "+c.br.readLine()+"\n");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}