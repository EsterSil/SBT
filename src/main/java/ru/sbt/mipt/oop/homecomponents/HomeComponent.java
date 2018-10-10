package ru.sbt.mipt.oop.homecomponents;

import jdk.internal.jline.internal.Nullable;

public interface HomeComponent {
    void changeState(String componentID, boolean state, @Nullable String parentComponentID, String statusMessage);
}
