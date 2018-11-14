package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.command.Command;


import java.util.HashMap;
import java.util.Map;

public class Controller implements RemoteControl {


    private final String rcID;
    private Map<String, Command> buttonMap = new HashMap<>();

    public Controller(String  rcID) {
        this.rcID = rcID;
    }

    public String getRcID() {
        return rcID;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttonMap.containsKey(buttonCode)) {
            buttonMap.get(buttonCode).execute();
        }
    }

    public Controller onButtonA(Command command) {
        buttonMap.put("A", command);
        return this;
    }

    public Controller onButtonB(Command command) {
        buttonMap.put("B", command);
        return this;
    }

    public Controller onButtonC(Command command) {
        buttonMap.put("C", command);
        return this;
    }

    public Controller onButtonD(Command command) {
        buttonMap.put("D", command);
        return this;
    }

    public Controller onButtonOne(Command command) {
        buttonMap.put("1", command);
        return this;
    }


    public Controller onButtonTwo(Command command) {
        buttonMap.put("2", command);
        return this;
    }

    public Controller onButtonThree(Command command) {
        buttonMap.put("3", command);
        return this;
    }

    public Controller onButtonFour(Command command) {
        buttonMap.put("4", command);
        return this;
    }
}
