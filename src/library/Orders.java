package library;

import library.books.Catalog;
import library.books.Copy;
import library.books.statuses.Status;
import library.exceptions.AlreadyOrderedException;
import library.exceptions.NoSuchCopyException;
import library.exceptions.NoSuchReaderException;
import library.reader.*;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Orders {
    private LinkedList<Order> orders = new LinkedList<>();
    private Map<Integer, Reader> readers;
    private Catalog catalog;
    private Borrows borrows;

    public Orders(Map<Integer, Reader> readers, Catalog catalog, Borrows borrows) {
        this.readers = readers;
        this.catalog = catalog;
        this.borrows = borrows;
    }

    public synchronized void addOrder(int readerId, int signature, library.OrderNotification orderNotification) throws NoSuchReaderException, NoSuchCopyException, AlreadyOrderedException {
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
        realizeOrders();
    }

    private boolean wasAlreadyOrdered(Order order) {
        return order.reader.orderedCopies.contains(order.copy.signature);
    }

    private void realizeOrders() {
        Iterator<Order> it = orders.iterator();
        while (it.hasNext()) {
            Order order = it.next();
            if(order.copy.status == Status.AVAILABLE) {
                it.remove();
                order.copy.status = Status.BORROWED;
                try {
                    order.notification.notify(order);
                    borrows.addBorrow(order.reader, order.copy);
                }
                catch (RemoteException e) {
                    order.reader.orderedCopies.remove(order.copy.signature);
                    order.copy.status = Status.AVAILABLE;
                }
            }
        }
    }

    public synchronized void returnOrderedCopy(int readerId, int signature) throws NoSuchReaderException, NoSuchCopyException {
        if(!readers.containsKey(readerId)) {
            throw new NoSuchReaderException();
        }
        Reader reader = readers.get(readerId);
        if(!reader.orderedCopies.contains(signature)) {
            throw new NoSuchCopyException();
        }

        reader.orderedCopies.remove(signature);
        catalog.getCopy(signature).status = Status.AVAILABLE;
        realizeOrders();
    }
}
