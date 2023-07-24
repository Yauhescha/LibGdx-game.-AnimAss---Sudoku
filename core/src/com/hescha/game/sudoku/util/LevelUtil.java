package com.hescha.game.sudoku.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class LevelUtil {

    public static ArrayList<Level> loadLevels() {

        FileHandle file = Gdx.files.internal("levels/levels.json");
        if (file.exists()) {
            String jsonData = file.readString();
            Json json = new Json();
            return json.fromJson(ArrayList.class, Level.class, jsonData);
        } else {
            return new ArrayList<>();
        }
    }
}
