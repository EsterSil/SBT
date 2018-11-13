package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.loarers.SmartHomeLoader;
import ru.sbt.mipt.oop.managers.EventManager;

import java.io.IOException;

// this class has one purpose - start event circle processing
public class Application {

    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        EventManager eventManager = context.getBean(EventManager.class);
        eventManager.runEventLoop();
    }

}
