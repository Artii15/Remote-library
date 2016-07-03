package library;

import library.books.Book;
import library.books.Catalog;
import library.books.CatalogPosition;
import library.books.Copy;
import library.reader.Reader;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

class LibraryService implements Library {
    private Catalog catalog = new Catalog();
    private HashMap<Integer, Reader> readers = new HashMap<>();

    @Override
    public int create(Book book) throws RemoteException {
        return catalog.insert(book);
    }

    @Override
    public int create(Copy copy) throws RemoteException, NoSuchElementException {
        if(catalog.contains(copy.bookId)) {
            return catalog.insert(copy);
        }
        else {
            throw new NoSuchElementException("Book with id " + copy.bookId + " doesn't exist");
        }
    }

    @Override
    public List<CatalogPosition> searchByTitle(String title) throws RemoteException {
        return catalog.searchByTitle(title);
    }

    @Override
    public int register(Reader reader) throws RemoteException {
        reader.id = ++Reader.highestId;
        readers.put(reader.id, reader);

        return reader.id;
    }
}
