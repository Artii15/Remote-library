package library.books;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Catalog {
    private HashMap<Integer, CatalogPosition> positions = new HashMap<>();

    public int insert(Book book) {
        book.id = ++Book.highestId;
        positions.put(book.id, new CatalogPosition(book));

        return book.id;
    }

    public int insert(Copy copy) {
        copy.signature = ++Copy.highestSignature;
        copy.status = Status.AVAILABLE;
        positions.get(copy.bookId).addCopy(copy);

        return copy.signature;
    }

    public boolean contains(int bookId) {
        return positions.containsKey(bookId);
    }

    public List<CatalogPosition> searchByTitle(String title) {
        String normalizedTitle = title.toLowerCase().trim();
        LinkedList<CatalogPosition> matchedPositions = new LinkedList<>();

        positions.forEach((id, position) -> {
            if(position.book.title.toLowerCase().trim().matches(String.format(".*%s.*", normalizedTitle))) {
                matchedPositions.add(position);
            }
        });

        return matchedPositions;
    }
}
