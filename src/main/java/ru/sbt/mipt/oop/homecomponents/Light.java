package ru.sbt.mipt.oop.homecomponents;

public class Light implements HomeLeaf {
    private boolean isOn;
    private final String id;

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

    @Override
    public Response changeState(String componentID, boolean state) {
        if (componentID.equals(this.id)) {
            this.setOn(state);
            return new Response(Status.OK_CHANGED, "Light " + this.id );
        }
        return  new Response(Status.NO_MATCHES);
    }
}
