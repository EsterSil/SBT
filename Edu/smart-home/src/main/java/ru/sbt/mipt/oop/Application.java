package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.command.AllLightsOffCommand;
import ru.sbt.mipt.oop.loarers.SmartHomeLoader;
import ru.sbt.mipt.oop.managers.EventManager;
import ru.sbt.mipt.oop.remotecontrol.Controller;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;

import java.io.IOException;

// this class has one purpose - start event circle processing
public class Application {

    public static void main(String... args) {



        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        RemoteControlRegistry registry = context.getBean(RemoteControlRegistry.class);
        //Controller controller = new Controller();

        //registry.registerRemoteControl();

        EventManager eventManager = context.getBean(EventManager.class);
        eventManager.runEventLoop();
    }



}
