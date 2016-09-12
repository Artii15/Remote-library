package library.books;

import java.io.Serializable;

public class Copy implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4644870103430750093L;

	static int highestSignature = 0;

    public int bookId;
    public Integer signature = null;
    
    public String publisher;
    public String  releaseDate;
    public Status status;
}