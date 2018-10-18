package ru.sbt.mipt.oop.events;


import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.Room;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.service.SensorService;

public class HallEventProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        if (!event.getType().equals(SensorEventType.DOOR_CLOSED)) {
            return;
        }
        smartHome.executeAction((Action<Room>) object -> {

            if (object.getName().equals("hall")) {
                smartHome.executeAction((Action<Light>) object1 -> object1.changeState(object1.getId(), false));
            }
            return null;
        });
    }
}
