package library;

import library.menu.Exit;
import library.menu.Menu;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public abstract class Client {
    private String name = null;
    protected Library library;

    public Client(String name) {
        this.name = name;
    }

    protected final void run() {
        Security.ensureSecurityManager();

        try {
            Registry registry = LocateRegistry.getRegistry();
            library = (Library) registry.lookup("library");

            System.out.println("Connection to library established");

            loop();
        }
        catch (Exception e) {
            System.out.println(((name == null) ? "Client" : name) + " crashed");
            e.printStackTrace();
        }
    }

    private void loop() {
        Menu menu = new Menu();
        addMenuPositions(menu);

        Exit exitAction = new Exit();
        menu.addAction(exitAction);

        while (!exitAction.exitConditionMet()) {
            menu.letUserChooseAction();
        }
    }

    protected abstract void addMenuPositions(Menu menu);
}
