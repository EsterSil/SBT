package ru.sbt.mipt.oop.alarm;

public class Signaling {

    private AlarmState state= new Disabled(this);

    private String secretCode = "0000";

    public Signaling() {
    }

    public Signaling(String secretCode) {
        this.secretCode = secretCode;
    }

    public void changeState(AlarmState state) {
        this.state = state;
    }

    public AlarmState getState() {
        return state;
    }

    public void activete(String code){
        if (state instanceof Active) {
            changeState(new Alarm(this));
        }
        secretCode = code;
        changeState(new Active(this));

    }
}
