import java.net.*;
import java.io.*;
import java.util.*;

class Receiver {
    public static void main(String[] args) {
        FileWriter fr = null;
        try {
            Socket s = new Socket("localhost", 2001);
            PrintStream ps = new PrintStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter filename to save: ");
            String filename = scanner.nextLine();
            fr = new FileWriter(filename, true);

            System.out.println("Enter path in server: ");
            String path = scanner.nextLine();
            ps.println(path);

            while (true) {
                String line = br.readLine();
                System.out.println(line);
                fr.append(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                fr.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}