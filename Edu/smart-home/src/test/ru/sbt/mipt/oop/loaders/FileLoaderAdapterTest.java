package ru.sbt.mipt.oop.loaders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.loarers.fileloader.FileLoaderAdapter;
import ru.sbt.mipt.oop.loarers.SmartHomeInt;
import ru.sbt.mipt.oop.loarers.SmartHomeLoader;

import java.io.IOException;

public class FileLoaderAdapterTest {
    @Test
    public void toBasicSmartHomeTest() {
        SmartHomeLoader loader = new FileLoaderAdapter();
        SmartHomeInt home = null;
        try {
            home = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(home);
        BasicSmartHome smartHome = home.toBasicSmartHome();
        Assertions.assertEquals(4, smartHome.getComponents().size());

    }


}
