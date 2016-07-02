package library.books;

import java.util.ArrayList;

class CatalogPosition {
    private Book book;
    private ArrayList<Copy> copies = new ArrayList<>();

    CatalogPosition(Book book) {
        this.book = book;
    }
}
