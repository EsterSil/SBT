package ru.sbt.mipt.oop.alarm;

public class Disabled implements AlarmState {
    private Signaling signaling;

    public Disabled(Signaling signaling) {
        this.signaling = signaling;
        System.out.println( "Signaling disabled");
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
