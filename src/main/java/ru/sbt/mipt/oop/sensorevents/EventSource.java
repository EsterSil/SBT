package ru.sbt.mipt.oop.sensorevents;

public interface EventSource {
    SensorEvent getNextSensorEvent();
}
