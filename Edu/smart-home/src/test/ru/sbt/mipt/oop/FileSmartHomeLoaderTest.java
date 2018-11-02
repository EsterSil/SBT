package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.loarers.FileSmartHomeLoader;
import ru.sbt.mipt.oop.loarers.SmartHomeLoader;

import java.io.IOException;

public class FileSmartHomeLoaderTest {

    @Test
    public void loadTest() {
        SmartHomeLoader loader = new FileSmartHomeLoader();
        SmartHome home = null;
        try {
            home = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(home != null);
        Assert.assertTrue(!home.getRooms().isEmpty());
    }
}
