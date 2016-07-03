package library.reader;

import library.Order;

import java.rmi.RemoteException;

public class OrderNotification implements library.OrderNotification {

    private ReaderClient client;

    public OrderNotification(ReaderClient client) {
        this.client = client;
    }

    @Override
    public void notify(Order order) throws RemoteException {
        client.acceptOrder(order);
    }
}
