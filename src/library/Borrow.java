package library;

import java.io.Serializable;
import java.util.Date;

import library.books.Book;
import library.books.Copy;
import library.reader.Reader;

public class Borrow implements Serializable {

	private static final long serialVersionUID = 2160298065349905633L;
	public Reader reader;
	public Book book;
	public Copy copy;
	public Date creationDate;
	
	public Borrow(Reader reader, Book book, Copy copy) {
		this.reader = reader;
		this.book = book;
		this.copy = copy;
		creationDate = new Date();
	}
}
