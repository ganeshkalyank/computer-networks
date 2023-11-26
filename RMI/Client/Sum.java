import java.rmi.*;

interface Sum extends Remote {
    int sum(int a, int b) throws RemoteException;
}