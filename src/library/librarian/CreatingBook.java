package library.librarian;

import library.Library;
import library.books.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

class CreatingBook extends LibrarianAction {
    CreatingBook(Library library) {
        super(library);
    }

    @Override
    public String getLabel() {
        return "Creating book";
    }

    @Override
    public void callback() {
        try {
            library.create(readInformationAboutBook());
        } catch (RemoteException e) {
            System.out.println("Book could not be created. Try again later");
        } catch (IOException e) {
            System.out.println("Invalid data provided");
        }
    }

    private Book readInformationAboutBook() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Book book = new Book();

        System.out.print("Title: ");
        book.title = reader.readLine();

        System.out.print("Author: ");
        book.author = reader.readLine();

        System.out.print("Description: ");
        book.description = reader.readLine();

        return book;
    }
}
