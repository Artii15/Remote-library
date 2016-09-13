package library.librarian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.List;

import library.Borrow;
import library.Library;
import library.exceptions.NoSuchReaderException;
import library.menu.LibraryAction;

public class ReaderHistoryBrowsing extends LibraryAction {

	public ReaderHistoryBrowsing(Library library) {
		super(library);
	}

	@Override
	public String getLabel() {
		return "Browsing reader history";
	}

	@Override
	public void callback() {
		try {
			printHistory(library.getReaderBorrowsHistory(readReaderId()));
		} catch (NumberFormatException e) {
			System.out.println("Invalid id format, id must be an integer number");
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println("Library temporary unavailable");
			e.printStackTrace();
		} catch (NoSuchReaderException e) {
			System.out.println("Reader with provided id does not exist");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Invalid data provided");
			e.printStackTrace();
		}
	}

	private int readReaderId() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Reader id: ");
        return Integer.parseInt(reader.readLine());
	}
	
	private void printHistory(List<Borrow> history) {
		System.out.println("Borrows history:");
		history.forEach(borrow -> {
			System.out.println(String.format(
					"\t %d %s %d %s", 
					borrow.book.getId(), borrow.book.title, borrow.copy.signature, borrow.creationDate));
		});
	}
}
