package ru.sbt.mipt.oop.loarers;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSmartHomeLoader implements SmartHomeLoader{

    public SmartHome load() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        return gson.fromJson(json, SmartHome.class);
    }


}
