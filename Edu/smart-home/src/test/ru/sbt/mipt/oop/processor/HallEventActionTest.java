package ru.sbt.mipt.oop.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.*;

@ExtendWith(MockitoExtension.class)
class HallEventActionTest {

    @Mock
    private RoomComponent roomMock;

    @Mock
    private LightComponent lightMock;

    @Mock
    private SensorEvent eventMock;
    @Mock
    private BasicSmartHome smartHome;
    @InjectMocks
    private  HallEventProcessor processor; // =  new HallEventProcessor(smartHome);



    @Test
    void executeActionOnRoomTest() {

        Mockito.when(eventMock.getType()).thenReturn(SensorEventType.DOOR_CLOSED);
        Mockito.doCallRealMethod().when(smartHome).executeAction(Mockito.any(Action.class));
        Mockito.doCallRealMethod().when(smartHome).addChild(Mockito.any(RoomComponent.class));
        smartHome.addChild(roomMock);
        processor.onEvent(eventMock);
        //Mockito.verify(smartHome, Mockito.times(2)).executeAction(Mockito.any());
        Mockito.verify(roomMock).executeAction(Mockito.any());
    }

    @Test
    void executeActionOnHomeFromRoomTest() {
        Mockito.when(eventMock.getType()).thenReturn(SensorEventType.DOOR_CLOSED);
        Mockito.doCallRealMethod().when(smartHome).executeAction(Mockito.any(Action.class));
        Mockito.doCallRealMethod().when(smartHome).addChild(Mockito.any(RoomComponent.class));
        RoomComponent room = new RoomComponent();
        room.setName("hall");
        smartHome.addChild(room);
        processor.onEvent(eventMock);
        Mockito.verify(smartHome, Mockito.atLeast(2)).executeAction(Mockito.any());
    }

    @Test
    void executeActionOnLightTest() {
        Mockito.when(eventMock.getType()).thenReturn(SensorEventType.DOOR_CLOSED);
        BasicSmartHome home = new BasicSmartHome();
        RoomComponent room = new RoomComponent();
        room.setName("hall");
        room.addChild(lightMock);
        home.addChild(room);
        processor = new HallEventProcessor(home);
        processor.onEvent(eventMock);
        Mockito.verify(lightMock, Mockito.atLeast(2)).executeAction(Mockito.any());

    }
}
