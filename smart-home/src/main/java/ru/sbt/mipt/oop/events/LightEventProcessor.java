package ru.sbt.mipt.oop.events;


import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.service.SensorService;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {
    final String TURNED_ON = " was turned on.";
    final String TURNED_OFF = " was turned off.";

    public void process(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        if (!isLightEvent(event)) {
            return;
        }
        smartHome.executeAction((Action<Light>) object -> {
            boolean state = event.getType() == LIGHT_ON;
            return object.changeState(event.getObjectId(), state);
        });
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType().equals(LIGHT_ON) || event.getType().equals(LIGHT_OFF);
    }

}
