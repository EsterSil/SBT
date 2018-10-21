package ru.sbt.mipt.oop.alarm;

public class Disabled implements AlarmState {
    private Signaling signaling;

    public Disabled(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void changeState(AlarmState state) {
        signaling.changeState(state);
    }
}
