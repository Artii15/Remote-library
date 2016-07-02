package library.books;

import java.io.Serializable;
import java.util.HashMap;

public class CatalogPosition implements Serializable {
    public Book book;
    public HashMap<Integer, Copy> copies = new HashMap<>();

    CatalogPosition(Book book) {
        this.book = book;
    }

    void addCopy(Copy copy) {
        copies.put(copy.signature, copy);
    }
}