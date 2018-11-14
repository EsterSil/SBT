package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.LightComponent;

public class AllLightsOnCommand implements Command {
    private final BasicSmartHome smartHome;

    public AllLightsOnCommand(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.executeAction(object1 -> {
            if (object1 instanceof LightComponent) {
                LightComponent light = (LightComponent) object1;
                light.changeState(light.getId(), true);
            }
        });
    }
}
