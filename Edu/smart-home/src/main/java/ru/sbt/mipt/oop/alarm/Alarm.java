package ru.sbt.mipt.oop.alarm;

public class Alarm implements AlarmState {
    private Signaling signaling;


    public Alarm(Signaling signaling) {
        this.signaling = signaling;
        System.out.println( "ALARM!");
    }

    @Override
    public void changeState(AlarmState state) {
        signaling.changeState(state);
    }

    @Override
    public void activate(String code) {

    }

    @Override
    public void deactivate(String code) {

    }
}
