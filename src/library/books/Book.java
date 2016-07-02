package library.books;

import java.io.Serializable;

public class Book implements Serializable {
    static int highestId = 0;

    Integer id = null;
    public String title;
    public String author;
    public String description;
}
