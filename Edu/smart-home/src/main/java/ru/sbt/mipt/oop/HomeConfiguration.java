package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.sbt.mipt.oop.loarers.FileSmartHomeLoader;
import ru.sbt.mipt.oop.loarers.SmartHomeLoader;
import ru.sbt.mipt.oop.processor.*;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
@Configuration
@ComponentScan
public class HomeConfiguration {

    private static SmartHome smartHome;
    private static EventManager manager;

    public HomeConfiguration() {
        try {
            smartHome = new FileSmartHomeLoader().load();
            //manager = new HomeEventObserver(smartHome);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@Autowired
    public HomeConfiguration(SmartHomeLoader smartHomeLoader, EventManager manager ) {
        try {
            smartHome = smartHomeLoader.load();
            manager = manager;
            configureManager(smartHome);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Bean
    EventManager eventManager() {
        manager = new EventManagerAdapter();
        configureManager(smartHome);
        return manager;
    }

    public void configureManager(SmartHome smartHome) {
        Collection<HomeEventProcessor> processors = configureEventProcessors(smartHome);
        for (HomeEventProcessor p: processors) {
            manager.addEventProcessor(p);
        }
    }

    private static Collection<HomeEventProcessor> configureEventProcessors(SmartHome smartHome) {
        Collection<HomeEventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(new SMSSenderDecorator(new SignalingDecorator(new LightEventProcessor(smartHome),
                smartHome), smartHome));
        eventProcessors.add(new SMSSenderDecorator(new SignalingDecorator(new DoorEventProcessor(smartHome), smartHome),
                smartHome));
        eventProcessors.add(new SMSSenderDecorator(new SignalingDecorator(new HallEventProcessor(smartHome), smartHome),
                smartHome));
        eventProcessors.add(new SignalingEventProcessor(smartHome));
        return eventProcessors;
    }
}
