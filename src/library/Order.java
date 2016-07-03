package library;

import library.books.Copy;
import library.reader.Reader;

public class Order {
    public Reader reader;
    public Copy copy;

    public Order(Reader reader, Copy copy) {
        this.reader = reader;
        this.copy = copy;
    }
}
