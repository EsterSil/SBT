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
        state.activate(code);
        /*
        if (state instanceof Activated) {
            changeState(new Alarm(this)) ;
        }
        secretCode = code;
        changeState(new Activated(this));
    */}

    public void deactivate (String code) {
        state.deactivate(code);
        /*
        if(state instanceof Disabled) {
            return;
        }
        if (code.equals(this.secretCode)) {
            changeState( new Disabled(this));
        } else {
            changeState(new Alarm(this));
        }*/
    }

    public boolean checkCodeConcept(String code) {
        return this.secretCode.equals(code);
    }
}
