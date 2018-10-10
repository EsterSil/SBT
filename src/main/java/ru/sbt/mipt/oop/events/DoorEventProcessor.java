package ru.sbt.mipt.oop.events;

import javafx.util.Pair;
import ru.sbt.mipt.oop.service.SensorService;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.Room;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.*;


public class DoorEventProcessor implements EventProcessor{
    public  void process(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        if (!isDoorEvent(event)){
            return;
        }

        if (event.getType() == DOOR_OPEN) {
            smartHome.changeState(event.getObjectId(), true, null, " was opened.");
        } else {
            smartHome.changeState(event.getObjectId(), false, null, " was closed.");
        }

    }



    private boolean isDoorEvent(SensorEvent event) {
        return event.getType().equals(DOOR_CLOSED) || event.getType().equals(DOOR_OPEN);
    }


}
