package library.books;

import java.util.HashMap;

class CatalogPosition {
    private Book book;
    private HashMap<Integer, Copy> copies = new HashMap<>();

    CatalogPosition(Book book) {
        this.book = book;
    }
}
