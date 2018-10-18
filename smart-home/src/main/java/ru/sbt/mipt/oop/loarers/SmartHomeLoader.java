package ru.sbt.mipt.oop.loarers;

import ru.sbt.mipt.oop.homecomponents.SmartHome;

import java.io.IOException;

public interface SmartHomeLoader {

    SmartHome load() throws IOException;
}
