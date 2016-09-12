package library.reader;

import library.Order;

import java.rmi.RemoteException;

public class OrderNotification implements library.OrderNotification {

    private ReaderClient reader;

    public OrderNotification(ReaderClient reader) {
        this.reader = reader;
    }

    @Override
    public void notify(Order order) throws RemoteException {
        reader.acceptOrder(order);
    }
}
