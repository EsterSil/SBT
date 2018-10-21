package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.*;



public class    DoorEventProcessor implements HomeEventProcessor {

    @Override
    public void onEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) {
            return;
        }
        smartHome.executeAction( object -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                boolean state = event.getType() == DOOR_OPEN;
                door.changeState(event.getObjectId(), state);
            }
        });
    }


    private boolean isDoorEvent(SensorEvent event) {
        return event.getType().equals(DOOR_CLOSED) || event.getType().equals(DOOR_OPEN);
    }

    private class DoorAction implements Action {

        @Override
        public void execute(Object object) {

        }
    }

}


