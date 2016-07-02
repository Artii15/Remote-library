package library;

import library.books.Book;
import library.books.Catalog;
import library.books.Copy;

import java.rmi.RemoteException;

class LibraryService implements Library {
    private Catalog catalog = new Catalog();

    @Override
    public void create(Book book) throws RemoteException {
        catalog.insert(book);
    }

    @Override
    public void create(Copy copy) throws RemoteException {

    }
}
