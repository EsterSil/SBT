package ru.sbt.mipt.oop.homecomponents;

public class Door implements HomeLeaf {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public Response changeState(String componentID, boolean state) {
        if (componentID.equals(this.id)) {
            this.setOpen(state);
           // System.out.println("Door " + this.id + "in room " + roomId + " " + statusMessage);
            return new Response(Status.OK_CHANGED, "Door " + this.id );
        }
        return new Response(Status.NO_MATCHES);
    }
}
