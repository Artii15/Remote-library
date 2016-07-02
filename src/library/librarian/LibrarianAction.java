package library.librarian;

import library.Library;
import library.menu.Action;

abstract class LibrarianAction implements Action {
    protected Library library;

    LibrarianAction(Library library) {
        this.library = library;
    }
}
