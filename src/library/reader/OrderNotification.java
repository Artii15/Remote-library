package library.reader;

import library.Order;

import java.rmi.RemoteException;

public class OrderNotification implements library.OrderNotification {

    @Override
    public void notify(Order order) throws RemoteException {
        System.out.println(String.format("Your order %d is ready", order.copy.signature));
    }
}
