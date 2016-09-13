package library.reader;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Reader implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6036319629156303644L;

	public static int highestId = 0;

    public Integer id = null;
    public String firstName;
    public String lastName;
    public HashSet<Integer> orderedCopies = new HashSet<>();
    public HashMap<Integer, PossessedCopy> possessedCopies = new HashMap<>();
}
