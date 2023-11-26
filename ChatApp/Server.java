import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
class Server {
    JFrame f;
    PrintStream ps;
    BufferedReader br;
    ServerSocket ss;
    Socket s;
    JTextArea log;
    Server() {
        f = new JFrame("Server");
        f.setSize(300, 300);

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
        Server s = new Server();
        try {
            s.ss = new ServerSocket(2001);
            s.s = s.ss.accept();
            s.ps = new PrintStream(s.s.getOutputStream());
            s.br = new BufferedReader(new InputStreamReader(s.s.getInputStream()));
        } catch (Exception e) {
            System.out.println(e);
        }
        while (true) {
            try {
                s.log.append(s.s.getInetAddress()+": "+s.br.readLine()+"\n");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}