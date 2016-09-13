package library.librarian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.List;

import library.Library;
import library.exceptions.NoSuchReaderException;
import library.menu.LibraryAction;
import library.reader.Reader;

public class ShowingReader extends LibraryAction {

	public ShowingReader(Library library) {
		super(library);
	}

	@Override
	public String getLabel() {
		return "Show reader";
	}

	@Override
	public void callback() {
		try {
            Reader reader = library.getReader(readReaderId());
            displayReader(reader);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Library temporary unavailable. Try again later.");
        } catch (IOException e) {
            System.out.println("Invalid data provided");
        } catch (NoSuchReaderException e) {
			System.out.println("Reader with provided id does not exist");
			e.printStackTrace();
		}
	}

	private int readReaderId() throws IOException {
        System.out.print("Searched reader name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }
	
	private void displayReader(Reader reader) {
		System.out.println(String.format("%d %s %s", reader.id, reader.firstName, reader.lastName));
		reader.possessedCopies.values().forEach(possessedCopy -> {
			System.out.println(String.format("\t %s %d", 
					possessedCopy.book.title, possessedCopy.copy.signature));
		});
	}
}
