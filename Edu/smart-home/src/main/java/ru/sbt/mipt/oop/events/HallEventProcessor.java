package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homecomponents.*;

public class HallEventProcessor implements HomeEventProcessor {
    @Override
    public void onEvent (SmartHome smartHome, SensorEvent event) {
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
