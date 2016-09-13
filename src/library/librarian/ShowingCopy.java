package library.librarian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

import library.Library;
import library.books.Copy;
import library.exceptions.NoSuchCopyException;
import library.exceptions.NoSuchReaderException;
import library.menu.LibraryAction;
import library.reader.Reader;

public class ShowingCopy extends LibraryAction {

	public ShowingCopy(Library library) {
		super(library);
	}

	@Override
	public String getLabel() {
		return "Display information about copy";
	}

	@Override
	public void callback() {
		try {
            Copy copy = library.getCopy(readSignature());
            displayCopy(copy);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Library temporary unavailable. Try again later.");
        } catch (IOException e) {
            System.out.println("Invalid data provided");
        } catch (NoSuchCopyException e) {
			System.out.println("Copy with provided signature does not exist");
			e.printStackTrace();
		}
	}

	private int readSignature() throws IOException {
        System.out.print("Copy signature: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }
	
	private void displayCopy(Copy copy) {
		System.out.println(String.format("%d %s", copy.signature, copy.status.display()));
	}
}
