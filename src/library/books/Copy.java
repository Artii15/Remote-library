package library.books;

import java.io.Serializable;

public class Copy implements Serializable {
    static int highestSignature = 0;

    public int bookId;
    public Integer signature = null;
    
    public String publisher;
    public String  releaseDate;
    public Status status;
}