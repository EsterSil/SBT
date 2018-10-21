package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.loarers.FileSmartHomeLoader;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import java.io.IOException;

// this class has one purpose - start event circle processing
public class Application {
    private static SmartHome smartHome; // common class, application do not need it's compounds
    private static EventSource eventSource; // common interface
    private static HomeEventObserver homeEventObserver;

    public Application(/*SmartHomeLoader smartHomeLoader, EventSource eventSource */) throws IOException {
        // initialize smart home via loader
        smartHome = new FileSmartHomeLoader().load();
        // connect event source
        eventSource = new EventGenerator();
        homeEventObserver = new HomeEventObserver(eventSource);
    }

    public static void main(String... args) throws IOException {
        Configurator.configure(homeEventObserver);
        homeEventObserver.runEventLoop(smartHome);
    }

}
