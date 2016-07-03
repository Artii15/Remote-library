package library;

import library.books.Copy;
import library.reader.Reader;

public class Order {
    public Reader reader;
    public Copy copy;
    public OrderNotification notification;

    public Order(Reader reader, Copy copy, OrderNotification notification) {
        this.reader = reader;
        this.copy = copy;
        this.notification = notification;
    }
}
