package ru.sbt.mipt.oop.homecomponents;

import java.util.Collection;

public class Room implements HomeComposite {
    //private Collection<Light> lights;
    //private Collection<Door> doors;
    private Collection<HomeComponent> components;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        //this.lights = lights;
        //this.doors = doors;
        components.addAll(lights);
        components.addAll(doors);
        this.name = name;
    }

    /*public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }
*/
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
    public void changeState(String componentID, boolean state, String parentComponentID, String statusMessage) {
        for (HomeComponent c: components) {
            c.changeState(componentID,state, this.name, statusMessage);
        }
    }
}
