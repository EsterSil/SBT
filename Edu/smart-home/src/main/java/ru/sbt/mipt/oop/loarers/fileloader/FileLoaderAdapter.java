package ru.sbt.mipt.oop.loarers;

import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class FileLoaderAdapter implements SmartHomeLoader {
    @Override
    public SmartHomeInt load() throws IOException {
        FileSmartHomeLoader loader = new FileSmartHomeLoader();
        SmartHome home = loader.load();
        return home.toBasicSmartHome();
    }
}
