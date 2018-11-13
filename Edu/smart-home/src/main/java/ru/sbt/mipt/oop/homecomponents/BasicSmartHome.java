package ru.sbt.mipt.oop.homecomponents;




import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.alarm.Signaling;
import ru.sbt.mipt.oop.loarers.SmartHomeInt;

import java.util.ArrayList;
import java.util.Collection;
@Component
public class BasicSmartHome implements HomeComposite, SmartHomeInt {
    private Collection<HomeComponent> components;
    public Collection<HomeComponent> getComponents() {
        return components;
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

    public BasicSmartHome() {
        components = new ArrayList<>();
        signaling = new Signaling();
    }

    public BasicSmartHome(Collection<? extends HomeComponent> components) {
        this.components = new ArrayList<>();
        this.components.addAll(components);
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

    @Override
    public BasicSmartHome toBasicSmartHome() {
        return this;
    }
}
