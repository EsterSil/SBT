package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homecomponents.Response;
import ru.sbt.mipt.oop.homecomponents.Status;
import ru.sbt.mipt.oop.service.SensorService;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.*;


public class DoorEventProcessor implements EventProcessor {
    private final String OPENED = " was opened.";
    private final String CLOSED = " was closed.";

    public void process(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        if (!isDoorEvent(event)) {
            return;
        }
        Response response = null;
        String statusMessage = "";
        if (event.getType() == DOOR_OPEN) {
            response = smartHome.changeState(event.getObjectId(), true);
            if (response.getStatus() == Status.OK_CHANGED) {
                statusMessage = OPENED;
            }

        } else {
            response = smartHome.changeState(event.getObjectId(), false);
            if (response.getStatus() == Status.OK_CHANGED) {
                statusMessage = CLOSED;
            }
        }
        System.out.println(response.getMessage() + statusMessage);

    }


    private boolean isDoorEvent(SensorEvent event) {
        return event.getType().equals(DOOR_CLOSED) || event.getType().equals(DOOR_OPEN);
    }


}
