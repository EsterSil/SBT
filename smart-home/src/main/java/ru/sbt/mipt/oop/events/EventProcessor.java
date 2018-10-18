package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.service.SensorService;

public interface EventProcessor {
    void process(SmartHome smartHome, SensorEvent event, SensorService eventService);
}
