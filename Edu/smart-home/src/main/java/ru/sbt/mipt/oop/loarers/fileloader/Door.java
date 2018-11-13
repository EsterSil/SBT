package ru.sbt.mipt.oop.loarers.fileloader;

import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.HomeLeaf;

public class Door {
    private final String id;
    private boolean isOpen;


    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private void setOpen(boolean open) {
        isOpen = open;
    }


    public boolean isOpen() {
        return isOpen;
    }
}

