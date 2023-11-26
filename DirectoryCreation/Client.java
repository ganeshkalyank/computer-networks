import java.net.*;
import java.io.*;
import java.util.*;

class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 2001);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps = new PrintStream(s.getOutputStream());
            String name = br.readLine();
            File f = new File("./"+name);
            f.mkdir();
            ps.println("Directory creation successful!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}