package ru.sbt.mipt.oop.homecomponents;

//import com.sun.javafx.scene.control.skin.VirtualFlow;



import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.Signaling;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComposite {
    private Collection<HomeComponent> components;
    private Collection<Room> rooms;
    private Signaling signaling;

    public SmartHome() {
        components = new ArrayList<>();
        signaling = new Signaling();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        signaling = new Signaling();
    }


    public Collection<HomeComponent> getComponents() {
        return components;
    }

    @Override
    public void addChild(HomeComponent component) {
        components.add(component);
    }

    @Override
    public void remove(HomeComponent component) {
        components.remove(component);
    }

    @Override
    public Collection<HomeComponent> getChildren() {
        return components;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        if (components == null) {
            components = new ArrayList<>();
            components.addAll(rooms);
        }
        components.forEach(c -> c.executeAction(action));
    }

    public boolean isHomeLocked() {
        return this.signaling.getState() instanceof Alarm;
    }

    public void activateSignaling(String code){
        this.signaling.activate(code);
    }
    public void deactivateSignaling(String code){
        this.signaling.deactivate(code);
    }
}
