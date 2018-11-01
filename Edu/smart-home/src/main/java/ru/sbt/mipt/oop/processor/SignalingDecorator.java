package ru.sbt.mipt.oop.processor;

import ru.sbt.mipt.oop.alarm.Activated;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class SignalingDecorator implements HomeEventProcessor {

    private final HomeEventProcessor processor;
    private final SmartHome smartHome;
    public SignalingDecorator(HomeEventProcessor processor, SmartHome smartHome) {
        this.processor = processor;
        this.smartHome = smartHome;
    }

    @Override
    public void onEvent( SensorEvent event) {
        if (smartHome.getSignaling().getState() instanceof Activated) {
            smartHome.getSignaling().setToAlarm();
        }
        processor.onEvent(event);
    }
}
