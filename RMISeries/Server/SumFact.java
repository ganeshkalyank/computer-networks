import java.rmi.*;

interface SumFact extends Remote {
    int sum(int n) throws RemoteException;
    int factorial(int n) throws RemoteException;
}