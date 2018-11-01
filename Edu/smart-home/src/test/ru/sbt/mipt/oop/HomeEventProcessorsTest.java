package ru.sbt.mipt.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.processor.DoorEventProcessor;
import ru.sbt.mipt.oop.processor.HomeEventProcessor;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.loarers.FileSmartHomeLoader;

import java.io.IOException;

public class HomeEventProcessorsTest {
    private SmartHome home;

    @BeforeEach
    public void init(){
        try {
            home = new FileSmartHomeLoader().load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DoorEventProcessorTest() {
        HomeEventProcessor processor = new DoorEventProcessor();


    }
}
