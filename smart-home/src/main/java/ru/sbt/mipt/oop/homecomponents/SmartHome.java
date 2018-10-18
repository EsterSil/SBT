package ru.sbt.mipt.oop.homecomponents;

//import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComposite {
    Collection<HomeComponent> components;

    public SmartHome() {
        components = new ArrayList<>();
    }

    public SmartHome(Collection<HomeComponent> components) {
        this.components = components;
    }


    public Collection<HomeComponent> getComponents() {
        return components;
    }


    public Response changeState(String componentID, boolean state) {
        /*for (HomeComponent c : components) {
            Response componentResponse = c.executeAction(componentID, state);
            if (componentResponse.getStatus() == Status.OK_CHANGED) {

                return componentResponse;
            }
        }
       */
        return new Response(Status.NO_MATCHES, "UNKNOWN OBJECT ID DETECTED! UPDATE HOME DATABASES");
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
        components.forEach(c -> c.executeAction(action));
    }
}
