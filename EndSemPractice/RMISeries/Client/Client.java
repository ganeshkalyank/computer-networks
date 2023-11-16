import java.rmi.*;
import java.util.Scanner;

class Client {
    public static void main(String[] args) {
        try {
            SumFact sf = (SumFact) Naming.lookup("rmi://localhost:1099/sumfact");
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            SumThread st = new SumThread(sf, n);
            FactorialThread ft = new FactorialThread(sf, n);
            st.start();
            ft.start();
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class SumThread extends Thread {
    SumFact sf;
    int n;
    SumThread(SumFact sf, int n) {
        this.sf = sf;
        this.n = n;
    }
    public void run() {
        try {
            System.out.println(sf.sum(n));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class FactorialThread extends Thread {
    SumFact sf;
    int n;
    FactorialThread(SumFact sf, int n) {
        this.sf = sf;
        this.n = n;
    }
    public void run() {
        try {
            System.out.println(sf.factorial(n));
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

