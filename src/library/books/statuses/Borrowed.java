package library.books.statuses;

import java.io.Serializable;

import library.reader.Reader;

public class Borrowed extends Status implements Serializable {

	private static final long serialVersionUID = 7346549027794035644L;
	Reader owner;
	
	public Borrowed(Reader owner) {
		this.owner = owner;
	}
	
	@Override
	public String display() {
		return String.format("Borrowed by: %d %s %s", owner.id, owner.firstName, owner.lastName);
	}
}
