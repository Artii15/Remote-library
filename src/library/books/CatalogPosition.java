package library.books;

import java.util.ArrayList;

class CatalogPosition {
    private Book book;
    private ArrayList<Copy> copies = new ArrayList<>();

    public CatalogPosition(Book book) {
        this.book = book;
    }
}
