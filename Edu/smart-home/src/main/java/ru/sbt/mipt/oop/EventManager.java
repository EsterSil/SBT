package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processor.HomeEventProcessor;

public interface EventManager {
    void runEventLoop();

    void addEventProcessor(HomeEventProcessor processor);

}
