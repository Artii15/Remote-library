package library.reader;

import library.Order;

import java.rmi.Remote;

public interface OrderNotification extends Remote {
    void notify(Order order);
}
