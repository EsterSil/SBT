package ru.sbt.mipt.oop.alarm;

public class Alarm implements AlarmState {
    private Signaling signaling;


    public Alarm(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void changeState(AlarmState state) {
        signaling.changeState(state);
    }
}
