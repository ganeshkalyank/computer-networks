import java.net.*;
import java.io.*;

class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 2001);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps = new PrintStream(s.getOutputStream());
            String path = br.readLine();
            File f = new File(path);
            if (f.exists()) {
                File[] files = f.listFiles();
                String filelist = "";
                for (File each : files ) {
                    filelist += each.getName()+", ";
                }
                ps.println(filelist);
            } else {
                ps.println("Path does not exist!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}