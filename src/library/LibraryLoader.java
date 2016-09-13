package library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LibraryLoader {
	
	private final String libraryStorage = "library.dat";
	
	public void save(LibraryService library) {
		try {
			FileOutputStream outputFile = new FileOutputStream(libraryStorage);
			ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
			outputStream.writeObject(library);
			outputStream.close();
			outputFile.close();
		}
		catch (IOException e) {
			System.out.println("Could not save library state");
			e.printStackTrace();
		}
	}
	
	public LibraryService restore() {
		LibraryService library = new LibraryService();
		
		try {
			FileInputStream fileIn = new FileInputStream(libraryStorage);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			library = (LibraryService) in.readObject();
			in.close();
			fileIn.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return library;
	}
}
