package ru.sbt.mipt.oop.events;

public interface EventSource {
    SensorEvent getNextSensorEvent();
}
