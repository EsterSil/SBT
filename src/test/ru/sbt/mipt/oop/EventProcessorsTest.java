package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.DoorEventProcessor;
import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.loarers.FileSmartHomeLoader;

import java.io.IOException;

public class EventProcessorsTest {
    private SmartHome home;

    @Before
    public void init(){
        try {
            home = new FileSmartHomeLoader().load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DoorEventProcessorTest() {
        EventProcessor processor = new DoorEventProcessor();

    }
}
