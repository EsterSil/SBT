package ru.sbt.mipt.oop.processor;
import ru.sbt.mipt.oop.events.SensorEvent;

public interface HomeEventProcessor {

    void onEvent( SensorEvent event);
}
