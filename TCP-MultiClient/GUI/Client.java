import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.Scanner;

class Client {
    JFrame f;
    Socket s;
    BufferedReader br;
    PrintStream ps;
    Scanner scanner;
    Client() {
        f = new JFrame("Client");

        JLabel msglabel = new JLabel("Enter message: ");
        msglabel.setBounds(20,20,200,20);
        f.add(msglabel);

        JTextField msg = new JTextField();
        msg.setBounds(20,40,200,20);
        f.add(msg);

        JButton send = new JButton("Send");
        send.setBounds(20,70,100,20);
        f.add(send);

        JLabel resp = new JLabel();
        resp.setBounds(20,100,200,20);
        f.add(resp);

        JLabel log = new JLabel("Log");
        log.setBounds(250,20,200,20);
        f.add(log);

        JTextArea logarea = new JTextArea();
        logarea.setBounds(250,40,200,200);
        logarea.setEditable(false);
        f.add(logarea);

        try {
            s = new Socket("localhost",2001);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ps = new PrintStream(s.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ps.println(msg.getText());
                logarea.append("To server: "+msg.getText()+"\n");
                resp.setText("Message sent successfully!");
            }
        });

        f.setSize(500,300);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            try {
                String rmsg = br.readLine();
                logarea.append("From server: "+rmsg+"\n");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void main(String args[]) {
        new Client();
    }
}
