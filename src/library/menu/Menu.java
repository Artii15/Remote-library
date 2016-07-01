package library.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
    private ArrayList<Action> positions = new ArrayList<>();

    public void addPosition(Action position) {
        positions.add(position);
    }

    public void letUserChooseAction() {
        display();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            positions.get(Integer.parseInt(reader.readLine())).callback();
        }
        catch (Exception e) {
            System.out.println("Invalid action choosen");
        }
    }

    private void display() {
        System.out.println("Choose action");
        for(int i = 0; i < positions.size(); ++i) {
            System.out.println(i + " - " + positions.get(i).getLabel());
        }
    }
}
