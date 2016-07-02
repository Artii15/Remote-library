package library.librarian;

import library.Library;
import library.books.Copy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

class CreatingCopy extends LibrarianAction {

    CreatingCopy(Library library) {
        super(library);
    }

    @Override
    public String getLabel() {
        return "Creating book copy";
    }

    @Override
    public void callback() {
        try {
            int copyId = library.create(readInformationAboutCopy());
            System.out.println("Created copy signature: " + copyId);
        } catch (RemoteException e) {
            System.out.println("Could not create copy. Try again later");
        } catch (IOException e) {
            System.out.println("Invalid data provided");
        }
    }

    private Copy readInformationAboutCopy() throws IOException {
        Copy copy = new Copy();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Book id: ");
        copy.bookId = Integer.parseInt(reader.readLine());

        System.out.print("Publisher: ");
        copy.publisher = reader.readLine();

        System.out.print("Release date: ");
        copy.releaseDate = reader.readLine();

        return copy;
    }
}
