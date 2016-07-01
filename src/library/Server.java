package library;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String args[]) {
        Security.ensureSecurityManager();

        try {
            LibraryService libraryService = new LibraryService();
            Library remoteLibrary = (Library) UnicastRemoteObject.exportObject(libraryService, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("library", remoteLibrary);
            System.out.println("Library server started");
        }
        catch (Exception e) {
            System.out.println("Library server exception");
            e.printStackTrace();
        }
    }
}
