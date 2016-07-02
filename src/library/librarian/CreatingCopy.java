package library.librarian;

import library.Library;

public class CreatingCopy extends LibrarianAction {

    CreatingCopy(Library library) {
        super(library);
    }

    @Override
    public String getLabel() {
        return "Create book copy";
    }

    @Override
    public void callback() {
        //library.create();
    }
}
