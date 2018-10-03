package ru.sbt.mipt.oop.smarthomeloarers;

import ru.sbt.mipt.oop.smarthomeobjects.SmartHome;

import java.io.IOException;

public interface SmartHomeLoader {

    SmartHome load() throws IOException;
}
