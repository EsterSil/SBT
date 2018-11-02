package ru.sbt.mipt.oop.homecomponents;

import java.util.ArrayList;
import java.util.Collection;

public class Room implements HomeComposite {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private Collection<HomeComponent> components;
    private String name;

    public void setLights(Collection<Light> lights) {
        this.lights = lights;
    }

    public void setDoors(Collection<Door> doors) {
        this.doors = doors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room() {
    }

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        components = new ArrayList<>();
        components.addAll(lights);
        components.addAll(doors);
        this.name = name;
    }

    public String getName() {
        return name;
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
        if (components == null || components.isEmpty()) {
            components = new ArrayList<>();
            components.addAll(doors);
            components.addAll(lights);
        }
        components.forEach((c) -> c.executeAction(action));
    }
}
