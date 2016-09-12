package library;

import library.books.Copy;
import library.reader.Reader;

import java.io.Serializable;

public class Order implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5785936386517989171L;
	public Reader reader;
    public Copy copy;
    public OrderNotification notification;

    public Order(Reader reader, Copy copy, OrderNotification notification) {
        this.reader = reader;
        this.copy = copy;
        this.notification = notification;
    }
}
