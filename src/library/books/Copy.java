package library.books;

public class Copy {
    private static int highestSignature = 0;

    public String publisher;
    public String  releaseDate;
    public Status status = Status.AVAILABLE;
    private int signature = ++highestSignature;
}
