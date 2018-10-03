package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.sensorevents.EventGenerator;
import ru.sbt.mipt.oop.sensorevents.EventProcessor;
import ru.sbt.mipt.oop.sensorevents.EventSource;
import ru.sbt.mipt.oop.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.service.SensorService;
import ru.sbt.mipt.oop.smarthomeloarers.FileSmartHomeLoader;
import ru.sbt.mipt.oop.smarthomeloarers.SmartHomeLoader;
import ru.sbt.mipt.oop.smarthomeobjects.SmartHome;

import java.io.IOException;
// this class has one purpose - start event circle processing
public class Application {
    private static SmartHome smartHome; // common class, application do not need it's compounds
    private static EventSource eventSource; // common interface

    public Application(/*SmartHomeLoader smartHomeLoader, */) throws IOException {
        // считываем состояние дома из файла
        smartHome = new FileSmartHomeLoader().load();
        eventSource = new EventGenerator();
    }

    public static void main(String... args) throws IOException {

        // начинаем цикл обработки событий
        runEventCircle();
    }

    private static void runEventCircle() {
        SensorEvent event = eventSource.getNextSensorEvent();
        ///  instead of Application class executing command, SensorService class was created
        SensorService sensorService = new SensorService();
        while (event != null) {
            System.out.println("Got event: " + event);
            switch (event.getType()){
                case LIGHT_ON:
                case LIGHT_OFF:
                    // событие от источника света
                    EventProcessor.processLightEvent(smartHome, event);
                    break;
                case DOOR_CLOSED:
                case DOOR_OPEN:
                    // событие от двери
                    EventProcessor.processDoorEvent(smartHome, event, sensorService);
                    break;

            }
            event = eventSource.getNextSensorEvent();
        }
    }

}
