package ru.sbt.mipt.oop.homecomponents;

//import com.sun.javafx.scene.control.skin.VirtualFlow;

import jdk.internal.jline.internal.Nullable;

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

    @Override
    public Response changeState(String componentID, boolean state) {
        for (HomeComponent c : components) {
            Response componentResponse = c.changeState(componentID, state);
            if (componentResponse.getStatus() == Status.OK_CHANGED) {

                return componentResponse;
            }
        }
        return new Response(Status.NO_MATCHES, "UNKNOWN OBJECT ID DETECTED! UPDATE HOME DATABASES");
    }

    public void turnLightsOff() {

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
}
