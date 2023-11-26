import java.rmi.*;

class RegisterSumImpl {
    public static void main(String[] args) {
        try {
            Sum s = new SumImpl();
            Naming.rebind("rmi://localhost:1099/sum", s);
            System.out.println("Registered successfully!");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}