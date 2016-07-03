package library;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OrderNotification extends Remote {
    void notify(Order order) throws RemoteException;
}
