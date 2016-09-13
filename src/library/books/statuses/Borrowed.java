package library.books.statuses;

import library.reader.Reader;

public class Borrowed extends Status {

	Reader owner;
	
	public Borrowed(Reader owner) {
		this.owner = owner;
	}
	
	@Override
	public String display() {
		return String.format("Borrowed by: %d %s %s", owner.id, owner.firstName, owner.lastName);
	}

}
