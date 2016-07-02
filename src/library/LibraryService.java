package library;

import library.books.Book;
import library.books.Catalog;
import library.books.Copy;

import java.rmi.RemoteException;
import java.util.NoSuchElementException;

class LibraryService implements Library {
    private Catalog catalog = new Catalog();

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
}
