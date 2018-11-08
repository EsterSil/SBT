package ru.sbt.mipt.oop;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.*;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.HomeComponent;
import ru.sbt.mipt.oop.processor.DoorEventProcessor;
import ru.sbt.mipt.oop.processor.HomeEventProcessor;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.loarers.FileSmartHomeLoader;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)

public class DoorEventProcessorTest {

    //@Rule
    //public MockitoRule mockitoRule =  MockitoJUnit.rule();



    public static SensorEvent doorClosedEvent;
    public static SensorEvent otherEvent;

    public static SensorEvent doorOpenEvent;
    @Mock
    public static SmartHome homeMock;// = Mockito.mock(SmartHome.class);

    @BeforeAll
    public static void init() {
        doorOpenEvent = Mockito.mock(SensorEvent.class);
        Mockito.when(doorOpenEvent.getType()).thenReturn(SensorEventType.DOOR_OPEN);
        doorClosedEvent = Mockito.mock(SensorEvent.class);
        Mockito.when(doorClosedEvent.getType()).thenReturn(SensorEventType.DOOR_CLOSED);
        otherEvent = Mockito.mock(SensorEvent.class);
        Mockito.when(otherEvent.getType()).thenReturn(SensorEventType.LIGHT_ON);
    }

    @Test
    public void executeActionOnSmartHomeWithDoorOpenEventTest() {


        HomeEventProcessor processor = new DoorEventProcessor(homeMock);
        processor.onEvent(doorOpenEvent);
        Mockito.verify(homeMock).executeAction(Mockito.any());
        Mockito.verifyNoMoreInteractions(homeMock);

    }
    @Test
    public void executeActionOnSmartHomeWithDoorClosedEventTest() {

        HomeEventProcessor processor = new DoorEventProcessor(homeMock);
        processor.onEvent(doorClosedEvent);
        Mockito.verify(homeMock).executeAction(Mockito.any());
        Mockito.verifyNoMoreInteractions(homeMock);

    }
    @Test
    public void executeActionOnSmartHomeWithOtherEventTest() {

        HomeEventProcessor processor = new DoorEventProcessor(homeMock);
        processor.onEvent(otherEvent);
        Mockito.verifyNoMoreInteractions(homeMock);

    }

}
