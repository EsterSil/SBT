package ru.sbt.mipt.oop.loarers;


import ru.sbt.mipt.oop.homecomponents.*;

import java.util.ArrayList;
import java.util.Collection;

public class Room {

    private Collection<Light> lights;
    public void setLights(Collection<Light> lights) {
        this.lights = lights;
    }

    private Collection<Door> doors;
    public void setDoors(Collection<Door> doors) {
        this.doors = doors;
    }

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Room() {
    }

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        //this.components = new ArrayList<>();
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }


    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }
}
