package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.eventsgenerator.EventSource;
import ru.sbt.mipt.oop.processor.HomeEventProcessor;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class HomeEventObserverTest {
    HomeEventObserver homeEventObserver;

    @Before
    public void init() {
        homeEventObserver = new HomeEventObserver(new EventSource() {
            private int counter;

            @Override
            public SensorEvent getNextSensorEvent() {
                counter++;
                if (counter <= 10) {
                    return new SensorEvent(SensorEventType.DOOR_OPEN, "1");
                } else {
                    return null;
                }
            }

        });
    }

    private class TestProcessor implements HomeEventProcessor {

        private int counter;

        @Override
        public void onEvent(SensorEvent event) {
            if (event.getType() == SensorEventType.DOOR_OPEN) {
                counter++;
            }
        }

        public int getCounter() {
            return counter;
        }
    }




    @Test
    public void observerTest() {
        HomeEventProcessor testProcessor = new TestProcessor();
        homeEventObserver.addEventProcessor(testProcessor);
        homeEventObserver.runEventLoop();
        Assert.assertEquals(10, ((TestProcessor) testProcessor).getCounter());

    }
}
