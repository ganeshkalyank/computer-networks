import java.net.*;
import java.io.*;
import java.util.*;

class Sender {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(2001);
            Socket s = ss.accept();
            PrintStream ps = new PrintStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String filename = br.readLine();
            File file = new File(filename);
            Scanner fr = new Scanner(file);
            while (fr.hasNextLine()) {
                String line = fr.nextLine();
                System.out.println(line);
                ps.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}