package library.books;

import java.io.Serializable;

public class Book implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4837239906367858895L;

	static int highestId = 0;

    Integer id = null;
    public String title;
    public String author;
    public String description;
    
    public Integer getId() {
    	return id;
    }
}
