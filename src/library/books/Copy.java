package library.books;

import java.util.Date;

public class Copy {
    private static int highestSignature = 0;

    public Book book;
    public Publisher publisher;
    public Date releaseDate;
    public Status status;
    private int signature;

    public Copy() {
        signature = ++highestSignature;
    }

    public int getSignature() {
        return signature;
    }
}
