package ru.sbt.mipt.oop.events;

import javafx.util.Pair;
import ru.sbt.mipt.oop.service.SensorService;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.Room;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {
    public void process(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        if (!isLightEvent(event)) {
            return;
        }
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

    private boolean isLightEvent(SensorEvent event) {
        return event.getType().equals(LIGHT_ON) || event.getType().equals(LIGHT_OFF);
    }

}
