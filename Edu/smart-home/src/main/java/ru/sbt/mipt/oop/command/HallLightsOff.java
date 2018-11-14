package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.LightComponent;
import ru.sbt.mipt.oop.homecomponents.RoomComponent;
import ru.sbt.mipt.oop.loarers.fileloader.Light;

public class HallLightsOff implements Command {
    private final BasicSmartHome smartHome;

    public HallLightsOff(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.executeAction( object ->  {
            if (object instanceof RoomComponent) {
                RoomComponent room = (RoomComponent) object;
                if (room.getName().equals("hall")) {
                    room.executeAction(object1 -> {
                        if (object1 instanceof LightComponent) {
                            LightComponent light = (LightComponent) object1;
                            light.changeState(light.getId(), true);
                        }
                    });
                }
            }
        });
    }
}
