package ru.sbt.mipt.oop.events;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public interface HomeEventProcessor {

    void onEvent(SmartHome smartHome, SensorEvent event);
}
