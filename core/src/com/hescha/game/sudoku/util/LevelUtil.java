package com.hescha.game.sudoku.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class LevelUtil {

    public static ArrayList<Level> loadLevels() {
        String jsonData = Gdx.files.internal("levels/levels.json").readString();
        Json json = new Json();
        return json.fromJson(ArrayList.class, Level.class, jsonData);
    }
}
