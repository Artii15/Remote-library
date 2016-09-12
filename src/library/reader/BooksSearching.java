package library.reader;

import library.Library;
import library.books.CatalogPosition;
import library.books.Copy;
import library.menu.LibraryAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.stream.Stream;

class BooksSearching extends LibraryAction {

    BooksSearching(Library library) {
        super(library);
    }

    @Override
    public String getLabel() {
        return "Books searching";
    }

    @Override
    public void callback() {
        try {
            Stream<CatalogPosition> catalogPositions = library.searchByTitle(readTitleFromUser());
            displayFoundPositions(catalogPositions);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Service temporary unavailable. Try again later.");
        } catch (IOException e) {
            System.out.println("Invalid data provided");
        }
    }

    private String readTitleFromUser() throws IOException {
        System.out.print("Searched title: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    private void displayFoundPositions(Stream<CatalogPosition> catalogPositions) {
    	catalogPositions.forEach(position -> {
    		System.out.println(position.book.title + ", " + position.book.author);
            displayCopies(position.copies.values());
    	});
    }

    private void displayCopies(Collection<Copy> copies) {
        for(Copy copy: copies) {
            System.out.println("\t" + copy.signature + " " + copy.publisher + " " + copy.status);
        }
    }
}
