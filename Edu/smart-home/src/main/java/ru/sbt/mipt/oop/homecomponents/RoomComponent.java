package ru.sbt.mipt.oop.homecomponents;


import java.util.ArrayList;
import java.util.Collection;

public class RoomComponent implements HomeComposite {

    public RoomComponent(Collection<HomeComponent> components, String name) {
        this.components = components;
        this.name = name;
    }

    private Collection<HomeComponent> components;

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public RoomComponent() {
        this.components = new ArrayList<>();
    }


    @Override
    public void addChild(HomeComponent component) {
        if (components == null)     components = new ArrayList<>();
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
        if (components != null)     components.forEach((c) -> c.executeAction(action));
    }
}
