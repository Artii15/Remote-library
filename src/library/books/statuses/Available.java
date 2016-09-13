package library.books.statuses;

import java.io.Serializable;

public class Available extends Status implements Serializable {
	
	private static final long serialVersionUID = 4202697928940275335L;

	@Override
	public String display() {
		return "Available";
	}
	
}
