package library.books;

import java.util.HashMap;

public class Catalog {
    private HashMap<Integer, CatalogPosition> positions = new HashMap<>();

    public void insert(Book book) {
        book.id = ++Book.highestId;
        positions.put(book.id, new CatalogPosition(book));
    }
}
