package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homecomponents.*;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements HomeEventProcessor {

    private boolean isLightEvent(SensorEvent event) {
        if (event == null) return false;
        return event.getType().equals(LIGHT_ON) || event.getType().equals(LIGHT_OFF);
    }

    @Override
    public void onEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) {
            return;
        }
        smartHome.executeAction(object -> {
            if (object instanceof  Light) {
                Light light = (Light) object;
                boolean state = event.getType() == LIGHT_ON;
                light.changeState(event.getObjectId(), state);

            }
        });
    }


}
