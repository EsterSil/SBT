package ru.sbt.mipt.oop.homecomponents;

//import com.sun.javafx.scene.control.skin.VirtualFlow;



import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComposite {
    Collection<HomeComponent> components;
    Collection<Room> rooms;


    public SmartHome() {
        components = new ArrayList<>();
    }

    //public SmartHome(Collection<HomeComponent> components) {
    //    this.components = components;
    //}
    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
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
}
