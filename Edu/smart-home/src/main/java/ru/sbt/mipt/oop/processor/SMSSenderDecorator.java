package ru.sbt.mipt.oop.processor;

import ru.sbt.mipt.oop.alarm.Activated;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class SMSSenderDecorator implements HomeEventProcessor {

    private final HomeEventProcessor processor;
    private final SmartHome smartHome;

    public SMSSenderDecorator(HomeEventProcessor processor, SmartHome smartHome) {
        this.processor = processor;
        this.smartHome = smartHome;
    }

    @Override
    public void onEvent(SensorEvent event) {
        if(smartHome.getSignaling().getState() instanceof Activated) {
            System.out.println("Sending sms: \"Invasion! Biu-Wiu! Signaling was set to ALARM\" ");
            return;
        }
        if (smartHome.getSignaling().getState() instanceof Alarm) {
            System.out.println("Sending sms: \"Someone in the home!\"");
        }
        processor.onEvent(event);
    }
}
