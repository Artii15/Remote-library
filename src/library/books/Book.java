package library.books;

import java.util.HashSet;
import java.util.Set;

public class Book {
    public String title;
    public Genre genre;
    public Author author;
    public String ISBN;
    public Set<String> tags = new HashSet<>();
    public String description;
}
