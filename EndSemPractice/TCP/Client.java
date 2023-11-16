import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

class Client {
    public static void main(String[] args) {
        Socket s;
        try {
            s = new Socket("localhost", 2001);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while (true) {
                String msgRcvd = br.readLine();
                String msg = "";
                for (int i=0;i<msgRcvd.length();i++) {
                    msg += (char) ((int)(msgRcvd.charAt(i)) - 3);
                }
                System.out.println(msg);   
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}