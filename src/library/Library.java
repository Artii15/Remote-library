package library;

import library.books.Book;
import library.books.CatalogPosition;
import library.books.Copy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;

public interface Library extends Remote {
    int create(Book book) throws RemoteException;
    int create(Copy copy) throws RemoteException, NoSuchElementException;
    List<CatalogPosition> searchByTitle(String title) throws RemoteException;
}
