package ru.sbt.mipt.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.DoorEventProcessor;
import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.loarers.FileSmartHomeLoader;

import java.io.IOException;

public class EventProcessorsTest {
    private SmartHome home;

    @BeforeEach
    public void init() {
        try {
            home = new FileSmartHomeLoader().load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DoorEventProcessorTest() {
        EventProcessor processor = new DoorEventProcessor();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        processor.process(home, event, null);
    }
}
