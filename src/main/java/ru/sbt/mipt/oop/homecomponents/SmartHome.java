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
    public void changeState(String componentID, boolean state, String parentComponentID, String statusMessage) {
        for (HomeComponent c: components) {
            c.changeState( componentID, state, "home", statusMessage);
        }
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
