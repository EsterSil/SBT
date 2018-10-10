package ru.sbt.mipt.oop.homecomponents;

public class Light implements HomeLeaf {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    private void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void changeState(String componentID, boolean state, String roomID, String statusMessage) {
        if (componentID.equals(this.id)) {
            this.setOn(state);
            System.out.println("Light " + this.id + " in room " + roomID + " " + statusMessage);
        }
    }
}
