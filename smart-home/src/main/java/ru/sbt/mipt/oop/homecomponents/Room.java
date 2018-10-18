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

    public Response changeState(String componentID, boolean state) {
       /* for (HomeComponent c: components) {
            Response componentResponse = c.executeAction(componentID,state);
            if (componentResponse.getStatus() == Status.OK_CHANGED) {
                componentResponse.updateMessage(" in the room "+this.name);
                return componentResponse;
            }
        }*/
        return new Response(Status.NO_MATCHES);
    }


    @Override
    public void executeAction(Action action) {
        action.execute(this);
        components.forEach((c) -> c.executeAction(action));
    }
}
