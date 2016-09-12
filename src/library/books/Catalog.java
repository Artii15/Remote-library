package library.books;

import library.exceptions.NoSuchCopyException;

import java.util.HashMap;
import java.util.stream.Stream;

public class Catalog {
    private HashMap<Integer, CatalogPosition> positions = new HashMap<>();
    private HashMap<Integer, Copy> copies = new HashMap<>();

    public synchronized int insert(Book book) {
        book.id = ++Book.highestId;
        positions.put(book.id, new CatalogPosition(book));

        return book.id;
    }

    public synchronized int insert(Copy copy) {
        copy.signature = ++Copy.highestSignature;
        copy.status = Status.AVAILABLE;
        positions.get(copy.bookId).addCopy(copy);
        copies.put(copy.signature, copy);

        return copy.signature;
    }

    public boolean contains(int bookId) {
        return positions.containsKey(bookId);
    }

    public Stream<CatalogPosition> searchByTitle(String title) {
        String normalizedTitle = title.toLowerCase().trim();
        return positions.values().stream().filter(position -> {
        	return position.book.title.toLowerCase().trim().matches(String.format(".*%s.*", normalizedTitle));
        });
    }

    public Copy getCopy(int signature) throws NoSuchCopyException {
        if(!copies.containsKey(signature)) {
            throw new NoSuchCopyException();
        }
        return copies.get(signature);
    }
}
