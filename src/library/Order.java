package library;

import library.books.Copy;
import library.reader.Reader;

import java.io.Serializable;

public class Order implements Serializable {
    public Reader reader;
    public Copy copy;
    public OrderNotification notification;

    public Order(Reader reader, Copy copy, OrderNotification notification) {
        this.reader = reader;
        this.copy = copy;
        this.notification = notification;
    }
}
