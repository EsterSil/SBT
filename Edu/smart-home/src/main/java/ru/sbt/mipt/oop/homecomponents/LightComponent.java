package ru.sbt.mipt.oop.homecomponents;

public class LightComponent implements HomeLeaf {
    private boolean isOn;
    private final String id;
    final String TURNED_ON = " was turned on.";
    final String TURNED_OFF = " was turned off.";

    public LightComponent(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public String getId() {
        return id;
    }

    private void setOn(boolean on) {
        isOn = on;
    }

    public void changeState(String componentID, boolean state) {
        if (componentID.equals(this.id)) {
            this.setOn(state);
            System.out.println("LightComponent " + this.id + (state ? TURNED_ON : TURNED_OFF));
        }
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }


}
