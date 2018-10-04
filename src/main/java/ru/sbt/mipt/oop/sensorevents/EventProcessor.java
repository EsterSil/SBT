package ru.sbt.mipt.oop.sensorevents;

import javafx.util.Pair;
import ru.sbt.mipt.oop.service.SensorService;
import ru.sbt.mipt.oop.smarthomeobjects.Door;
import ru.sbt.mipt.oop.smarthomeobjects.Light;
import ru.sbt.mipt.oop.smarthomeobjects.Room;
import ru.sbt.mipt.oop.smarthomeobjects.SmartHome;

import static ru.sbt.mipt.oop.sensorevents.SensorEventType.*;


public class EventProcessor {
    public static void processDoorEvent(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        Pair<Door, Room> involved = smartHome.getDoorRoomByID(event.getObjectId());
        Door involvedDoor = involved.getKey();
        Room involvedRoom = involved.getValue();

        if (involvedDoor == null || involvedRoom == null) {
            return;
        }

        if (event.getType() == DOOR_OPEN) {
            involvedDoor.setOpen(true);
            System.out.println("Door " + involvedDoor.getId() + " in room " + involvedRoom.getName() + " was opened.");
        } else {
            involvedDoor.setOpen(false);
            System.out.println("Door " + involvedDoor.getId() + " in room " + involvedRoom.getName() + " was closed.");
            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
            if (involvedRoom.getName().equals("hall")) {
                smartHome.offLightTotal(eventService);
            }
        }

    }


    public static void processLightEvent(SmartHome smartHome, SensorEvent event) {

        Pair<Light, Room> involved = smartHome.getLightByID(event.getObjectId());
        Light involvedLight = involved.getKey();
        Room involvedRoom = involved.getValue();
        if (involvedLight == null || involvedRoom == null) {
            return;
        }

        if (event.getType() == LIGHT_ON) {
            involvedLight.setOn(true);
            System.out.println("Light " + involvedLight.getId() + " in room " + involvedRoom.getName() + " was turned on.");
        } else {
            involvedLight.setOn(false);
            System.out.println("Light " + involvedLight.getId() + " in room " + involvedRoom.getName() + " was turned off.");
        }
    }


}
