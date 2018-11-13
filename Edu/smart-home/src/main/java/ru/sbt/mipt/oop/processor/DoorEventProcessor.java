package ru.sbt.mipt.oop.processor;

import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.DoorComponent;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

import static ru.sbt.mipt.oop.eventsgenerator.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.eventsgenerator.SensorEventType.DOOR_OPEN;


public class DoorEventProcessor implements HomeEventProcessor {

    private final BasicSmartHome smartHome;

    public DoorEventProcessor(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void onEvent(SensorEvent event) {
        if (!isDoorEvent(event)) {
            return;
        }

        smartHome.executeAction(object -> {
            if (object instanceof DoorComponent) {
                DoorComponent door = (DoorComponent) object;
                boolean state = event.getType() == DOOR_OPEN;
                door.changeState(event.getObjectId(), state);
            }
        });
    }


    private boolean isDoorEvent(SensorEvent event) {
        if (event == null) {
            return false;
        }
        SensorEventType t = event.getType();
        return t.equals(DOOR_OPEN) || t.equals(DOOR_CLOSED) ;
    }

    private class DoorAction implements Action {

        @Override
        public void execute(Object object) {

        }
    }

}


