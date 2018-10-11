package ru.sbt.mipt.oop.events;

import javafx.util.Pair;
import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.homecomponents.*;
import ru.sbt.mipt.oop.service.SensorCommand;
import ru.sbt.mipt.oop.service.SensorService;

public class HallEventProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event, SensorService eventService) {
        if (!event.getType().equals(SensorEventType.DOOR_CLOSED)){
            return;
        }
        /*
       smartHome.changeState(event.getObjectId(), )

                light.set(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                eventService.sendCommand(command);
            }
        }
*/
    }
}
