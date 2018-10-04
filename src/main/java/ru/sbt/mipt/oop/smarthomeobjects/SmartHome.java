package ru.sbt.mipt.oop.smarthomeobjects;

//import com.sun.javafx.scene.control.skin.VirtualFlow;

import javafx.util.Pair;
import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.service.SensorCommand;
import ru.sbt.mipt.oop.service.SensorService;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public  Pair<Light, Room> getLightByID( String Id) {
        for (Room room : this.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(Id)) {
                    return new Pair<>(light, room);
                }
            }
        }
        return new Pair<>(null, null);
    }

    public  Pair<Door, Room> getDoorRoomByID( String Id) {
        for (Room room : this.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(Id)) {
                    return new Pair<>(door, room);
                }
            }
        }
        return new Pair<>(null, null);
    }

    public void offLightTotal(SensorService eventService) {
        for (Room homeRoom : this.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                eventService.sendCommand(command);
            }
        }
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }
}
