package library;

import library.books.Book;
import library.books.Copy;

import java.rmi.RemoteException;
import java.util.HashMap;

class LibraryService implements Library {
    private HashMap<String, Book> books = new HashMap<>();

    @Override
    public void create(Book book) throws RemoteException {
        books.put(book.ISBN, book);
    }

    @Override
    public void create(Copy copy) throws RemoteException {

    }
}
