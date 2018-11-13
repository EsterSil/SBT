package ru.sbt.mipt.oop.homecomponents;




import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.Signaling;

import java.util.ArrayList;
import java.util.Collection;
@Component
public class SmartHome implements HomeComposite {
    private Collection<HomeComponent> components;
    public Collection<HomeComponent> getComponents() {
        return components;
    }
    public void setRooms(Collection<Room> rooms) {
        if (this.components == null )   this.components = new ArrayList<>();
        this.components.addAll(rooms);
    }

    public Signaling getSignaling() {
        return signaling;
    }
    private Signaling signaling;
    public void activateSignaling(String code){
        this.signaling.activate(code);
    }
    public void deactivateSignaling(String code){
        this.signaling.deactivate(code);
    }

    public SmartHome() {
        components = new ArrayList<>();
        signaling = new Signaling();
    }

    public SmartHome(Collection<Room> rooms) {
        this.components = new ArrayList<>();
        this.components.addAll(rooms);
        this.signaling = new Signaling();
    }


    @Override
    public void addChild(HomeComponent component) {
        if(components == null)  components = new ArrayList<>();
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
        if (components != null )    components.forEach(c -> c.executeAction(action));
    }

}
