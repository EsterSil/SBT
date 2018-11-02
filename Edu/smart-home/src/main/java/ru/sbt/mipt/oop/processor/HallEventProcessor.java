package ru.sbt.mipt.oop.processor;

import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.Room;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class HallEventProcessor implements HomeEventProcessor {
    private final SmartHome smartHome;

    public HallEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void onEvent(SensorEvent event) {
        if (!event.getType().equals(SensorEventType.DOOR_CLOSED)) {
            return;
        }
        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    smartHome.executeAction(object1 -> {
                        if (object1 instanceof Light) {
                            Light light = (Light) object1;
                            light.changeState(light.getId(), false);
                        }
                    });
                    System.out.println(" Hall door was closed. All lights off");
                }
            }
        });
    }
}
