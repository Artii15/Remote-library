package library.reader;

import library.Library;
import library.menu.LibraryAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderRegistration extends LibraryAction {

    public ReaderRegistration(Library library) {
        super(library);
    }

    @Override
    public String getLabel() {
        return "Reader registration";
    }

    @Override
    public void callback() {
        try {
            int readerId = library.register(readInformationAboutReader());
            System.out.printf("New reader id: %d\n", readerId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Reader readInformationAboutReader() throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        Reader reader = new Reader();
        System.out.print("First name: ");
        reader.firstName = inputReader.readLine();

        System.out.print("Last name: ");
        reader.lastName = inputReader.readLine();

        return reader;
    }
}
