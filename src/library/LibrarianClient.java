package library;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LibrarianClient {
    public static void main(String[] args) {
        Security.ensureSecurityManager();

        try {
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Library library = (Library) registry.lookup("library");

            System.out.println("Library ready");
        }
        catch (Exception e) {
            System.out.println("Librarian client crashed");
            e.printStackTrace();
        }
    }
}
