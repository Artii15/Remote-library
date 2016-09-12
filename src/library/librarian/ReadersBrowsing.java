package library.librarian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.List;

import library.Library;
import library.books.CatalogPosition;
import library.menu.LibraryAction;
import library.reader.Reader;

public class ReadersBrowsing extends LibraryAction {

	public ReadersBrowsing(Library library) {
		super(library);
	}

	@Override
	public String getLabel() {
		return "Search for reader";
	}

	@Override
	public void callback() {
		try {
            List<Reader> readers = library.findReadersByName(readNameFromUser());
            displayFoundReaders(readers);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Service temporary unavailable. Try again later.");
        } catch (IOException e) {
            System.out.println("Invalid data provided");
        }
	}

	private String readNameFromUser() throws IOException {
        System.out.print("Searched reader name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
	
	private void displayFoundReaders(List<Reader> readers) {
		readers.forEach(reader -> {
			System.out.println(String.format("%d %s %s", reader.id, reader.firstName, reader.lastName));
		});
	}
}
