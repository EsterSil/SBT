package ru.sbt.mipt.oop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.loarers.FileSmartHomeLoader;
import ru.sbt.mipt.oop.loarers.SmartHomeInt;
import ru.sbt.mipt.oop.loarers.SmartHomeLoader;

import java.io.IOException;

public class FileSmartHomeLoaderTest {

    @Test
    public void loadTest() {
        SmartHomeLoader loader = new FileSmartHomeLoader();
        SmartHomeInt home = null;
        try {
            home = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(home);

        BasicSmartHome smartHome = home.toBasicSmartHome();

        Assertions.assertTrue(!smartHome.getComponents().isEmpty());
    }
}
