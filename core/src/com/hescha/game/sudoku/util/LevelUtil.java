package com.hescha.game.sudoku.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.hescha.game.sudoku.model.SudokuDifficulty;
import com.hescha.game.sudoku.service.SudokuGenerator;

import java.util.ArrayList;

public class LevelUtil {

    public static ArrayList<Level> loadLevels() {
        FileHandle file = Gdx.files.internal("levels/levels.json");
        String jsonData = file.readString();
        Json json = new Json();
        return json.fromJson(ArrayList.class, Level.class, jsonData);
    }
}
