package library.librarian;

import library.Library;
import library.books.Book;
import library.menu.Action;

import java.rmi.RemoteException;

public class CreatingBook implements Action {
    private Library library;

    public CreatingBook(Library library) {
        this.library = library;
    }

    @Override
    public String getLabel() {
        return "Creating book";
    }

    @Override
    public void callback() {
        try {
            library.create(new Book());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
