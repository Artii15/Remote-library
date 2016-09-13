package library.librarian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.List;

import library.Borrow;
import library.Library;
import library.exceptions.NoSuchCopyException;
import library.menu.LibraryAction;

public class CopyHistoryBrowsing extends LibraryAction {

	public CopyHistoryBrowsing(Library library) {
		super(library);
	}

	@Override
	public void callback() {
		try {
			printHistory(library.getCopyBorrowsHistory(readSignature()));
		} catch (NumberFormatException e) {
			System.out.println("Invalid signature format, signature must be an integer number");
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println("Library temporary unavailable");
			e.printStackTrace();
		} catch (NoSuchCopyException e) {
			System.out.println("Copy with provided signature does not exist");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Invalid data provided");
			e.printStackTrace();
		}
	}

	private int readSignature() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Signature: ");
        return Integer.parseInt(reader.readLine());
	}
	
	private void printHistory(List<Borrow> history) {
		System.out.println("Borrows history:");
		history.forEach(borrow -> {
			System.out.println(String.format(
					"\t %d %s %s %s", 
					borrow.reader.id, borrow.reader.firstName, borrow.reader.lastName, borrow.creationDate));
		});
	}

	@Override
	public String getLabel() {
		return "Browse copy history";
	}
}
