package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.DoorComponent;
import ru.sbt.mipt.oop.homecomponents.RoomComponent;

public class HallDoorCloseCommand implements Command {

    private final BasicSmartHome smartHome;

    public HallDoorCloseCommand(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.executeAction(object -> {
            if (object instanceof RoomComponent) {
                RoomComponent room = (RoomComponent)object;
                if (room.getName().equals("hall")) {
                    room.executeAction(object1 -> {
                        if (object1 instanceof DoorComponent) {
                            DoorComponent door = (DoorComponent) object1;
                            door.changeState(door.getId(), false);
                        }
                    });
                }
            }
        });
    }
}
