package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.processor.HomeEventProcessor;
@Component
public class EventManagerAdapter implements EventManager {
    private SensorEventsManager manager;

    public EventManagerAdapter() {
        this.manager = new SensorEventsManager();
    }

    @Override
    public void runEventLoop() {
        manager.start();
    }

    @Override
    public void addEventProcessor(HomeEventProcessor processor) {
        manager.registerEventHandler(new HandlerProcessorAdapter(processor));
    }
}
