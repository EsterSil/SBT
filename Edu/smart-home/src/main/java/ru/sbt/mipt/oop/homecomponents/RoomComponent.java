package ru.sbt.mipt.oop.homecomponents;


import java.util.ArrayList;
import java.util.Collection;

public class Room implements HomeComposite {

    private Collection<Light> lights;
    public void setLights(Collection<Light> lights) {
        if (this.components == null) this.components = new ArrayList<>();
        this.components.addAll(lights);
    }

    private Collection<Door> doors;
    public void setDoors(Collection<Door> doors) {
        if (this.components == null) this.components = new ArrayList<>();
        this.components.addAll(doors);
    }

    private Collection<HomeComponent> components;

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Room() {
        this.components = new ArrayList<>();
    }

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.components = new ArrayList<>();
        this.components.addAll(lights);
        this.components.addAll(doors);
        this.name = name;
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
