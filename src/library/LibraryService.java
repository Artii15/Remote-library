package library;

import library.books.Book;
import library.books.Catalog;
import library.books.Copy;
import library.books.DuplicateBookEntryException;

import java.rmi.RemoteException;

class LibraryService implements Library {
    private Catalog catalog = new Catalog();

    @Override
    public void create(Book book) throws RemoteException, DuplicateBookEntryException {
        if(catalog.contains(book)) {
            throw new DuplicateBookEntryException();
        }
        else {
            catalog.insert(book);
        }
    }

    @Override
    public void create(Copy copy) throws RemoteException {

    }
}
