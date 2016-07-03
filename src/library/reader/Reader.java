package library.reader;

import java.io.Serializable;
import java.util.HashSet;

public class Reader implements Serializable {
    public static int highestId = 0;

    public Integer id = null;
    public String firstName;
    public String lastName;
    public HashSet<Integer> orderedCopies = new HashSet<>();
}
