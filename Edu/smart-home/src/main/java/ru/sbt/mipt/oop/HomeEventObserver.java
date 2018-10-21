package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventObserver {
    private static Collection<HomeEventProcessor> eventProcessors = null;
    private EventSource eventSource;

    public HomeEventObserver(EventSource eventSource) {
        this.eventSource = eventSource;
    }

    public void runEventLoop(SmartHome smartHome) {
        SensorEvent event = eventSource.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            publish(smartHome, event, eventProcessors);
            event = eventSource.getNextSensorEvent();
        }
    }

    private static void publish(SmartHome smartHome, SensorEvent event, Collection<HomeEventProcessor> eventProcessors) {
        for (HomeEventProcessor p : eventProcessors) {
            p.onEvent(smartHome, event);
        }
    }

    public void addEventProcessor(HomeEventProcessor processor) {
        eventProcessors = new ArrayList<>();
        eventProcessors.add(processor);
    }

    public void addAllEventProcessors( Collection<HomeEventProcessor> processors) {
        eventProcessors.addAll(processors);
    }

}
