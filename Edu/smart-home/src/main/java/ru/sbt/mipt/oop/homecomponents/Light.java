package ru.sbt.mipt.oop.homecomponents;

public class Light implements HomeLeaf {
    private boolean isOn;
    private final String id;
    final String TURNED_ON = " was turned on.";
    final String TURNED_OFF = " was turned off.";
    private boolean lock;

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

    public void changeState(String componentID, boolean state) {
        if (this.isLock()) {
            System.out.println(" ALARM!  Someone trying to change state of light "+ id +
                    "\n Sending sms \n");;
            return;
        }
        if (componentID.equals(this.id)) {
            this.setOn(state);
            System.out.println("Light " + this.id + (state ? TURNED_ON : TURNED_OFF));
        }
    }

    public boolean isLock() {
        return lock;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }

    @Override
    public void lock(boolean lock) {
        this.lock = lock;
    }
}
