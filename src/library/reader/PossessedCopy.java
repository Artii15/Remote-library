package library.reader;

import java.io.Serializable;

import library.books.Book;
import library.books.Copy;

public class PossessedCopy implements Serializable {
	
	private static final long serialVersionUID = 3374352162420538024L;
	public Book book;
	public Copy copy;
	
	public PossessedCopy(Book book, Copy copy) {
		this.book = book;
		this.copy = copy;
	}
}
