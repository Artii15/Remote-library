package library;

import library.books.Catalog;
import library.books.Copy;
import library.books.Status;
import library.exceptions.AlreadyOrderedException;
import library.exceptions.NoSuchCopyException;
import library.exceptions.NoSuchReaderException;
import library.reader.*;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Map;

public class Orders {
    private LinkedList<Order> orders = new LinkedList<>();
    private Map<Integer, Reader> readers;
    private Catalog catalog;

    public Orders(Map<Integer, Reader> readers, Catalog catalog) {
        this.readers = readers;
        this.catalog = catalog;
    }

    public void addOrder(int readerId, int signature, library.OrderNotification orderNotification) throws NoSuchReaderException, NoSuchCopyException, AlreadyOrderedException {
        if(!readers.containsKey(readerId)) {
            throw new NoSuchReaderException();
        }
        Reader reader = readers.get(readerId);
        Copy copy = catalog.getCopy(signature);

        Order order = new Order(reader, copy, orderNotification);

        if(wasAlreadyOrdered(order)) {
            throw new AlreadyOrderedException();
        }
        reader.orderedCopies.add(copy.signature);
        orders.add(order);
        realizeOrder();
    }

    private boolean wasAlreadyOrdered(Order order) {
        return order.reader.orderedCopies.contains(order.copy.signature);
    }

    private void realizeOrder() {
        if(!orders.isEmpty()) {
            Order order = orders.removeFirst();
            order.copy.status = Status.BORROWED;
            try {
                order.notification.notify(order);
            } catch (RemoteException e) {
                order.reader.orderedCopies.remove(order.copy.signature);
                order.copy.status = Status.AVAILABLE;
                System.out.println("Could not send copy to the client");
            }
        }
    }
}
