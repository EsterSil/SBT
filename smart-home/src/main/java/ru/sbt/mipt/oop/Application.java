package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.loarers.FileSmartHomeLoader;
import ru.sbt.mipt.oop.service.SensorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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
        Collection<EventProcessor> eventProcessors = configureEventProcessors();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor p : eventProcessors) {
                p.process(smartHome, event, sensorService);
            }
            event = eventSource.getNextSensorEvent();
        }
    }

    private static Collection<EventProcessor> configureEventProcessors() {
        Collection<EventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(new LightEventProcessor());
        eventProcessors.add(new DoorEventProcessor());
        eventProcessors.add(new HallEventProcessor());
        return eventProcessors;
    }

}
