package ru.sbt.mipt.oop.events;

import javafx.util.Pair;
import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.service.SensorCommand;
import ru.sbt.mipt.oop.service.SensorService;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.Room;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class HallEventProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        if (!event.getType().equals(SensorEventType.DOOR_CLOSED)){
            return;
        }
        Pair<Door, Room> involved = smartHome.getDoorRoomByID(event.getObjectId());
        Door involvedDoor = involved.getKey();
        Room involvedRoom = involved.getValue();

        if (involvedDoor == null || involvedRoom == null) {
            return;
        }
        if (!involvedRoom.getName().equals("hall")) {
            return;
        }
        for (Room homeRoom : smartHome.getComponents()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                eventService.sendCommand(command);
            }
        }

    }
}
