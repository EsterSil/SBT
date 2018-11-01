package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.HomeLeaf;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class SignalingEventProcessor implements HomeEventProcessor {
    @Override
    public void onEvent(SmartHome smartHome, SensorEvent event) {
        if (!isSignalingEvent(event)) {
            return;
        }
        if(event.getType()== SensorEventType.ALARM_ACTIVATE){
            smartHome.activateSignaling(event.getObjectId());
        } else {
            smartHome.deactivateSignaling(event.getObjectId());
        }
        smartHome.executeAction(new Action() {
            @Override
            public void execute(Object object) {
                if (object instanceof HomeLeaf) {
                    HomeLeaf leaf = (HomeLeaf) object;
                    leaf.lock(smartHome.isHomeLocked());
                }
            }
        });
    }

    private boolean isSignalingEvent(SensorEvent event) {
        return event.getType().equals(SensorEventType.ALARM_ACTIVATE)
                || event.getType().equals(SensorEventType.ALARM_DEACTIVATE);
    }
}
