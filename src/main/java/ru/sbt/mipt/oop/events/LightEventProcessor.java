package ru.sbt.mipt.oop.events;

import javafx.util.Pair;
import ru.sbt.mipt.oop.homecomponents.*;
import ru.sbt.mipt.oop.service.SensorService;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {
    final String TURNED_ON = " was turned on.";
    final String TURNED_OFF = " was turned off.";
    public void process(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        if (!isLightEvent(event)) {
            return;
        }

        Response response = null;
        String statusMessage = "";
        if (event.getType() == LIGHT_ON) {
            response = smartHome.changeState(event.getObjectId(),true);
           if (response.getStatus() == Status.OK_CHANGED){
               statusMessage = TURNED_ON;
           }
        } else {
            response = smartHome.changeState(event.getObjectId(),false);
            if (response.getStatus() == Status.OK_CHANGED){
                statusMessage = TURNED_OFF;
            }
        }
        System.out.println(response.getMessage()+ statusMessage);
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType().equals(LIGHT_ON) || event.getType().equals(LIGHT_OFF);
    }

}
