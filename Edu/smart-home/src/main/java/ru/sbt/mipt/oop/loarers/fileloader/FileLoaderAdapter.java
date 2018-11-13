package ru.sbt.mipt.oop.loarers.fileloader;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.loarers.SmartHomeInt;
import ru.sbt.mipt.oop.loarers.SmartHomeLoader;

import java.io.IOException;
//@Component
public class FileLoaderAdapter implements SmartHomeLoader {
    @Override
    public SmartHomeInt load() throws IOException {
        FileSmartHomeLoader loader = new FileSmartHomeLoader();
        SmartHome home = loader.load();
        return home.toBasicSmartHome();
    }
}
