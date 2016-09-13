package library;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import library.books.Catalog;
import library.books.Copy;
import library.reader.Reader;

public class Borrows {
	private Catalog catalog;
	private HashMap<Integer, List<Borrow>> copiesBorrows = new HashMap<>();
	private HashMap<Integer, List<Borrow>> readersBorrows = new HashMap<>();
	
	public Borrows(Catalog catalog) {
		this.catalog = catalog;
	}
	
	public synchronized void addBorrow(Reader reader, Copy copy) {
		Borrow borrow = new Borrow(reader, catalog.getBook(copy.bookId), copy);
		
		if(!copiesBorrows.containsKey(copy.signature)) {
			copiesBorrows.put(copy.signature, new LinkedList<Borrow>());
		}
		
		if(!readersBorrows.containsKey(reader.id)) {
			readersBorrows.put(reader.id, new LinkedList<Borrow>());
		}
		
		copiesBorrows.get(copy.signature).add(borrow);
		readersBorrows.get(reader.id).add(borrow);
	}
	
	public List<Borrow> getReaderBorrows(int readerId) {
		return readersBorrows.get(readerId);
	}
	
	public List<Borrow> getCopyBorrows(int signature) {
		return copiesBorrows.get(signature);
	}
}
