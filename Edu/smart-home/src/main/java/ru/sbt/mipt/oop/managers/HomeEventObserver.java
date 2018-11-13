package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventsgenerator.*;
import ru.sbt.mipt.oop.processor.HomeEventProcessor;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventObserver implements EventManager{
    private static Collection<HomeEventProcessor> eventProcessors = null;
    private EventSource eventSource;
    private BasicSmartHome smartHome;

    public HomeEventObserver(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public HomeEventObserver(EventSource eventSource) {
        this.eventSource = eventSource;
    }

    public void runEventLoop() {
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
