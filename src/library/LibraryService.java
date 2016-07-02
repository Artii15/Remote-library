package library;

import library.books.Book;
import library.books.Catalog;
import library.books.Copy;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

class LibraryService implements Library {
    private Catalog catalog = new Catalog();

    @Override
    public int create(Book book) throws RemoteException {
        return catalog.insert(book);
    }

    @Override
    public int create(Copy copy) throws RemoteException {
        if(catalog.contains(copy.bookId)) {
            return catalog.insert(copy);
        }
        else {
            throw new NoSuchObjectException("Book with id " + copy.bookId + " does not exists");
        }
    }
}
