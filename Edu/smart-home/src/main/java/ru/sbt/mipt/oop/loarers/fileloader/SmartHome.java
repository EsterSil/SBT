package ru.sbt.mipt.oop.loarers;

import ru.sbt.mipt.oop.homecomponents.*;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements SmartHomeInt {
    private Collection<Room> rooms;

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public SmartHome() {
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public BasicSmartHome toBasicSmartHome() {
        Collection<HomeComponent> homeComponents = new ArrayList<>();
        for (Room r: rooms) {
            Collection<HomeComponent> roomComponents = new ArrayList<>();
            for (Door d: r.getDoors()) {
                 roomComponents.add(new DoorComponent(d.isOpen(),d.getId()));
            }
            for (Light l: r.getLights()) {
                roomComponents.add(new LightComponent( l.getId(), l.isOn()));
            }
             homeComponents.add(new RoomComponent(roomComponents, r.getName()));
        }
        return new BasicSmartHome(homeComponents);

    }
}
