package ru.sbt.mipt.oop.processor;

import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import static ru.sbt.mipt.oop.eventsgenerator.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.eventsgenerator.SensorEventType.DOOR_OPEN;


public class DoorEventProcessor implements HomeEventProcessor {

    private final SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void onEvent(SensorEvent event) {
        if (!isDoorEvent(event)) {
            return;
        }
        //System.out.println("got door");
        smartHome.executeAction(object -> {
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


