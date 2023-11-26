import java.rmi.*;
import java.rmi.server.*;

class SumFactImpl extends UnicastRemoteObject implements SumFact {
    SumFactImpl() throws RemoteException {
        super();
    }
    public int sum(int n) {
        int res = 0;
        for (int i=1;i<=n;i++) {
            res+=i*i*i;
        }
        return res;
    }
    public int factorial(int n) {
        if (n==0) return 1;
        return n*factorial(n-1);
    }
}