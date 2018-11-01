package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processor.*;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class Configurator {
    public static void configure(HomeEventObserver homeEventObserver, SmartHome smartHome) {
        homeEventObserver.addAllEventProcessors(configureEventProcessors(smartHome));
    }

    private static Collection<HomeEventProcessor> configureEventProcessors(SmartHome smartHome) {
        Collection<HomeEventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(new SMSSenderDecorator(new SignalingDecorator(new LightEventProcessor(smartHome),
                smartHome), smartHome));
        eventProcessors.add(new SMSSenderDecorator(new SignalingDecorator(new DoorEventProcessor(smartHome), smartHome),
                smartHome));
        eventProcessors.add(new SMSSenderDecorator(new SignalingDecorator(new HallEventProcessor(smartHome), smartHome),
                smartHome));
        eventProcessors.add(new SignalingEventProcessor(smartHome));
        return eventProcessors;
    }
}
