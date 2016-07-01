package library.librarian;

import library.Library;
import library.Security;
import library.menu.Exit;
import library.menu.Menu;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LibrarianClient {
    public static void main(String[] args) {
        Security.ensureSecurityManager();

        try {
            Registry registry = LocateRegistry.getRegistry();
            Library library = (Library) registry.lookup("library");

            System.out.println("Library ready");

            loop(library);
        }
        catch (Exception e) {
            System.out.println("Librarian client crashed");
            e.printStackTrace();
        }
    }

    private static void loop(Library library) {
        Menu menu = new Menu();
        menu.addPosition(new CreatingBook(library));
        Exit exitAction = new Exit();
        menu.addPosition(exitAction);

        while (!exitAction.exitConditionMet()) {
            menu.letUserChooseAction();
        }
    }
}
