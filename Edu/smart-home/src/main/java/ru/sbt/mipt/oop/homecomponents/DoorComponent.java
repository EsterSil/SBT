package ru.sbt.mipt.oop.homecomponents;

public class DoorComponent implements HomeLeaf {
    private final String id;
    private boolean isOpen;
    private final String OPENED = " was opened.";
    private final String CLOSED = " was closed.";


    public DoorComponent(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private void setOpen(boolean open) {
        isOpen = open;
    }

    public void changeState(String componentID, boolean state) {
        if (componentID.equals(this.id)) {
            this.setOpen(state);
            System.out.println( "DoorComponent " + this.id + (state ? OPENED : CLOSED));
        }
    }
    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }
}

