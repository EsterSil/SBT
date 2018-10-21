package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.DoorEventProcessor;
import ru.sbt.mipt.oop.events.HallEventProcessor;
import ru.sbt.mipt.oop.events.HomeEventProcessor;
import ru.sbt.mipt.oop.events.LightEventProcessor;

import java.util.ArrayList;
import java.util.Collection;

public class Configurator {
    public static void configure(HomeEventObserver homeEventObserver) {

    }

    private static Collection<HomeEventProcessor> configureEventProcessors() {
        Collection<HomeEventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(new LightEventProcessor());
        eventProcessors.add(new DoorEventProcessor());
        eventProcessors.add(new HallEventProcessor());
        return eventProcessors;
    }
}
