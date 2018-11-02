package ru.sbt.mipt.oop.homecomponents;

//import com.sun.javafx.scene.control.skin.VirtualFlow;



import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.Signaling;

import java.util.ArrayList;
import java.util.Collection;
@Component
public class SmartHome implements HomeComposite {
    private Collection<HomeComponent> components;

    public Collection<Room> getRooms() {
        return rooms;
    }

    private Collection<Room> rooms;

    public Signaling getSignaling() {
        return signaling;
    }

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

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        if (components.isEmpty()) {
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
