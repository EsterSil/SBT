package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class SignalingEventProcessor implements HomeEventProcessor {
    @Override
    public void onEvent(SmartHome smartHome, SensorEvent event) {
        if (!isSignalingEvent(event)) {
            return;
        }
        smartHome.executeAction(new Action() {
            @Override
            public void execute(Object object) {

            }
        });
    }

    private boolean isSignalingEvent(SensorEvent event) {
        return event.getType().equals(SensorEventType.ALARM_ACTIVATE)
                || event.getType().equals(SensorEventType.ALARM_DEACTIVATE);
    }
}
