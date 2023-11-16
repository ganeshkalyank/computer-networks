import java.rmi.*;

class RegisterSumFactImpl {
    public static void main(String[] args) {
        try {
            SumFact sf = new SumFactImpl();
            Naming.rebind("rmi://localhost:1099/sumfact", sf);
            System.out.println("Registered successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}