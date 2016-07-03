package library.reader;

import library.Client;
import library.Order;
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
        menu.addAction(new ReaderRegistration(library));
        menu.addAction(new BookOrdering(library));
    }

    public void acceptOrder(Order order) {
        System.out.println(String.format("Your order %d is ready", order.copy.signature));
    }
}
