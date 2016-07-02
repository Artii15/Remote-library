package library.librarian;

import library.Client;
import library.Library;
import library.menu.Exit;
import library.menu.Menu;

public class LibrarianClient extends Client {

    private LibrarianClient() {
        super("Librarian client");
    }

    public static void main(String[] args) {
        LibrarianClient client = new LibrarianClient();
        client.run();
    }

    @Override
    protected void addMenuPositions(Menu menu) {
        menu.addAction(new CreatingBook(library));
        menu.addAction(new CreatingCopy(library));
    }
}
