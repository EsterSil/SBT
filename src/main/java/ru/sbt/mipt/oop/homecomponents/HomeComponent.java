package ru.sbt.mipt.oop.homecomponents;


public interface HomeComponent {
    Response changeState(String componentID, boolean state);
}
