package library.books;

public class Copy {
    public static int highestSignature = 0;

    public int bookId;
    public Integer signature = null;
    
    public String publisher;
    public String  releaseDate;
    public Status status = Status.AVAILABLE;
}