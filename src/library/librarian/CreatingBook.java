package library.librarian;

import library.Library;
import library.books.Book;
import library.menu.LibraryAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

class CreatingBook extends LibraryAction {
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
            int createdBookId = library.create(readInformationAboutBook());
            System.out.println("Created book id: " + createdBookId);
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
