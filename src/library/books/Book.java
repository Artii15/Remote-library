package library.books;

import java.io.Serializable;

public class Book implements Serializable {
    public static int highestId = 0;

    public Integer id = null;
    public String title;
    public String author;
    public String description;
}
