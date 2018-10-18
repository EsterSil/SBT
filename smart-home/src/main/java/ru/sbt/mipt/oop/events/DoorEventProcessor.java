package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.service.SensorService;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;


public class DoorEventProcessor implements EventProcessor {
    private final String OPENED = " was opened.";
    private final String CLOSED = " was closed.";

    public void process(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        if (!isDoorEvent(event)) {
            return;
        }

        smartHome.executeAction((Action<Door>) object -> {
            boolean state = event.getType() == DOOR_OPEN;
            return object.changeState(event.getObjectId(), state);
        });
    }


    private boolean isDoorEvent(SensorEvent event) {
        return event.getType().equals(DOOR_CLOSED) || event.getType().equals(DOOR_OPEN);
    }


}
