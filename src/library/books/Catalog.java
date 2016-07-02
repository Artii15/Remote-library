package library.books;

import java.util.HashMap;

public class Catalog {
    private HashMap<String, CatalogPosition> positions = new HashMap<>();

    public boolean contains(Book book) {
        return positions.containsKey(book.ISBN);
    }

    public void insert(Book book) {
        positions.put(book.ISBN, new CatalogPosition(book));
    }
}
