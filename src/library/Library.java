package library;

import library.books.Book;
import library.books.CatalogPosition;
import library.books.Copy;
import library.exceptions.AlreadyOrderedException;
import library.exceptions.NoSuchCopyException;
import library.exceptions.NoSuchReaderException;
import library.reader.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;

public interface Library extends Remote {
    int create(Book book) throws RemoteException;
    int create(Copy copy) throws RemoteException, NoSuchElementException;
    List<CatalogPosition> findBooksByTitle(String title) throws RemoteException;
    List<Reader> findReadersByName(String name);
    int register(Reader reader) throws RemoteException;
    void order(int readerId, int signature, OrderNotification orderNotification) throws RemoteException, AlreadyOrderedException, NoSuchCopyException, NoSuchReaderException;
    void returnOrderedCopy(int readerId, int signature) throws RemoteException, NoSuchReaderException, NoSuchCopyException;
}
