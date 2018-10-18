package ru.sbt.mipt.oop.homecomponents;

public interface Action<T> {
    Response execute(T object);
}
