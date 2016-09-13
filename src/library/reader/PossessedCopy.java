package library.reader;

import library.books.Book;
import library.books.Copy;

public class PossessedCopy {

	public Book book;
	public Copy copy;
	
	public PossessedCopy(Book book, Copy copy) {
		this.book = book;
		this.copy = copy;
	}
}
