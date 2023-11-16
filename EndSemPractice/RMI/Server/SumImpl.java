import java.rmi.*;
import java.rmi.server.*;

class SumImpl extends UnicastRemoteObject implements Sum {
    SumImpl() throws RemoteException {
        super();
    }
    public int sum(int a, int b) {
        return a+b;
    }
}