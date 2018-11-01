package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.processor.HomeEventProcessor;
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
            publish( event, eventProcessors);
            event = eventSource.getNextSensorEvent();
        }
    }

    private static void publish( SensorEvent event, Collection<HomeEventProcessor> eventProcessors) {
        for (HomeEventProcessor p : eventProcessors) {
            p.onEvent(event);
        }
    }

    public void addEventProcessor(HomeEventProcessor processor) {
        if (eventProcessors == null) {
            eventProcessors = new ArrayList<>();
        }
        eventProcessors.add(processor);
    }

    public void addAllEventProcessors( Collection<HomeEventProcessor> processors) {
        if (eventProcessors == null) {
            eventProcessors = new ArrayList<>();
        }
        eventProcessors.addAll(processors);
    }

}
