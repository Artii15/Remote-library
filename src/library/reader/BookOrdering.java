package library.reader;

import library.Library;
import library.exceptions.AlreadyOrderedException;
import library.exceptions.NoSuchCopyException;
import library.exceptions.NoSuchReaderException;
import library.menu.LibraryAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BookOrdering extends LibraryAction {

    private ReaderClient client;

    public BookOrdering(Library library, ReaderClient client) {
        super(library);
        this.client = client;
    }

    @Override
    public String getLabel() {
        return "Book ordering";
    }

    @Override
    public void callback() {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Reader id: ");
            int readerId = Integer.parseInt(inputReader.readLine());

            System.out.print("Book copy signature: ");

            int signature = Integer.parseInt(inputReader.readLine());

            library.OrderNotification notification = (library.OrderNotification) UnicastRemoteObject.exportObject(new OrderNotification(client), 0);

            library.order(readerId, signature, notification);
            System.out.println("Book ordered");
        } catch (RemoteException e) {
            System.out.println("Could not order book. Try again later");
        } catch (IOException e) {
            System.out.println("Invalid data provided");
        } catch (AlreadyOrderedException e) {
            System.out.println("You have already ordered this book");
        } catch (NoSuchReaderException e) {
            System.out.println("Invalid reader id. Register in library before you try to borrow books.");
        } catch (NoSuchCopyException e) {
            System.out.println("Copy with such signature does not exist");
        }
    }
}
