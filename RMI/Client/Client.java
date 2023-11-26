import java.rmi.*;

class Client {
    public static void main(String[] args) {
        try {
            Sum s = (Sum) Naming.lookup("rmi://localhost:1099/sum");
            System.out.println(s.sum(5, 10));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}