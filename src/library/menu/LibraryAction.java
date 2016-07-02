package library.menu;

import library.Library;

public abstract class LibraryAction implements Action {
    protected Library library;

    public LibraryAction(Library library) {
        this.library = library;
    }
}
