package library.books;

import library.exceptions.NoSuchCopyException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Catalog {
    private HashMap<Integer, CatalogPosition> positions = new HashMap<>();
    private HashMap<Integer, Copy> copies = new HashMap<>();

    public int insert(Book book) {
        book.id = ++Book.highestId;
        positions.put(book.id, new CatalogPosition(book));

        return book.id;
    }

    public int insert(Copy copy) {
        copy.signature = ++Copy.highestSignature;
        copy.status = Status.AVAILABLE;
        positions.get(copy.bookId).addCopy(copy);
        copies.put(copy.signature, copy);

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

    public Copy getCopy(int signature) throws NoSuchCopyException {
        if(!copies.containsKey(signature)) {
            throw new NoSuchCopyException();
        }
        return copies.get(signature);
    }
}
