package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

// this class has one purpose - start event circle processing
public class Application {

    public static void main(String... args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }

}
