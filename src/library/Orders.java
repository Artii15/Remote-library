package library;

import library.books.Catalog;
import library.books.Copy;
import library.exceptions.AlreadyOrderedException;
import library.exceptions.NoSuchCopyException;
import library.exceptions.NoSuchReaderException;
import library.reader.Reader;

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

    public void addOrder(int readerId, int signature) throws NoSuchReaderException, NoSuchCopyException, AlreadyOrderedException {
        if(!readers.containsKey(readerId)) {
            throw new NoSuchReaderException();
        }
        Reader reader = readers.get(readerId);
        Copy copy = catalog.getCopy(signature);

        Order order = new Order(reader, copy);

        if(wasAlreadyOrdered(order)) {
            throw new AlreadyOrderedException();
        }
        reader.orderedCopies.add(copy.signature);
        orders.add(new Order(reader, copy));
        realizeOrder();
    }

    private boolean wasAlreadyOrdered(Order order) {
        return order.reader.orderedCopies.contains(order.copy.signature);
    }

    private void realizeOrder() {
        if(!orders.isEmpty()) {
            Order order = orders.removeFirst();

        }
    }
}
