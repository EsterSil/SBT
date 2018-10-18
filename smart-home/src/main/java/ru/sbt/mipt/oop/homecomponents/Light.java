package ru.sbt.mipt.oop.homecomponents;

public class Light implements HomeLeaf {
    private final String id;
    private boolean isOn;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public String getId() {
        return id;
    }

    private void setOn(boolean on) {
        isOn = on;
    }


    public Response changeState(String componentID, boolean state) {
        if (componentID.equals(this.id)) {
            this.setOn(state);
            return new Response(Status.OK_CHANGED, "Light " + this.id);
        }
        return new Response(Status.NO_MATCHES);
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }
}
