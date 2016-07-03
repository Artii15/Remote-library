package library;

import library.books.Book;
import library.books.Catalog;
import library.books.CatalogPosition;
import library.books.Copy;
import library.exceptions.AlreadyOrderedException;
import library.exceptions.CopyNotAvailableException;
import library.exceptions.NoSuchCopyException;
import library.exceptions.NoSuchReaderException;
import library.reader.*;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

class LibraryService implements Library {
    private HashMap<Integer, Reader> readers = new HashMap<>();
    private Catalog catalog = new Catalog();
    private Orders orders = new Orders(readers, catalog);

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

    @Override
    public void order(int readerId, int signature, library.OrderNotification orderNotification) throws RemoteException, AlreadyOrderedException, NoSuchCopyException, NoSuchReaderException, CopyNotAvailableException {
        orders.addOrder(readerId, signature, orderNotification);
    }

    @Override
    public void returnOrderedCopy(int readerId, int signature) throws RemoteException, NoSuchReaderException, NoSuchCopyException {
        orders.returnOrderedCopy(readerId, signature);
    }
}
