package library.menu;

public class Exit implements Action {
    private boolean doExit = false;

    @Override
    public String getLabel() {
        return "Exit";
    }

    @Override
    public void callback() {
        doExit = true;
    }

    public boolean exitConditionMet() {
        return doExit;
    }
}
