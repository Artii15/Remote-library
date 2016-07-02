package library.reader;

import library.Client;
import library.menu.Menu;

public class ReaderClient extends Client {

    private ReaderClient() {
        super("Reader client");
    }

    public static void main(String[] args) {
        ReaderClient client = new ReaderClient();
        client.run();
    }

    @Override
    protected void addMenuPositions(Menu menu) {
        menu.addAction(new BooksSearching(library));
    }
}
