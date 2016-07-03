package library.reader;

import library.Library;
import library.exceptions.NoSuchCopyException;
import library.exceptions.NoSuchReaderException;
import library.menu.LibraryAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BooksReturning extends LibraryAction {

    public BooksReturning(Library library) {
        super(library);
    }

    @Override
    public String getLabel() {
        return "Returning books";
    }

    @Override
    public void callback() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Reader id: ");
            int readerId = Integer.parseInt(reader.readLine());

            System.out.print("Copy signature: ");
            int signature = Integer.parseInt(reader.readLine());

            library.returnOrderedCopy(readerId, signature);
            System.out.println("Copy returned");
        } catch (NoSuchReaderException e) {
            System.out.println("Reader with provided id does not exist");
        } catch (IOException e) {
            System.out.println("Invalid data provided");
        } catch (NoSuchCopyException e) {
            System.out.println("Provided reader doesn't have copy with such signature");
        }

    }
}
