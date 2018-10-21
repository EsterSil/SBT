package ru.sbt.mipt.oop.alarm;

public class Active implements AlarmState {
    Signaling signaling;

    public Active(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void changeState( AlarmState state) {
        signaling.changeState(state);
    }
}
