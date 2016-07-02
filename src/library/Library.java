package library;

import library.books.Book;
import library.books.Copy;
import library.books.DuplicateBookEntryException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Library extends Remote {
    void create(Book book) throws RemoteException, DuplicateBookEntryException;
    void create(Copy copy) throws RemoteException;
}
