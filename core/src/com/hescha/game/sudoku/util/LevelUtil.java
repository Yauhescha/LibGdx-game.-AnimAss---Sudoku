package com.hescha.game.sudoku.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.hescha.game.sudoku.model.SudokuDifficulty;
import com.hescha.game.sudoku.service.SudokuGenerator;

import java.util.ArrayList;

public class LevelUtil {

    public static ArrayList<Level> loadLevels() {

//        ArrayList<Level> levels = new ArrayList<>();
//        levels.add(level);
//        return levels;
        FileHandle file = Gdx.files.internal("levels/levels.json");
//        if (file.exists()) {
            String jsonData = file.readString();
            Json json = new Json();
            return json.fromJson(ArrayList.class, Level.class, jsonData);
//        } else {
//
//
//            Level level = new Level();
//            level.setCategory("Cat");
//            level.setSudoku(SudokuGenerator.generateGame(SudokuDifficulty.FOR_KIDS));
//            level.setName("girst level");
//            level.setImagePath("ui/button.png");
//
//            ArrayList<Level> levels = new ArrayList<>();
//            levels.add(level);
//            Gdx.files.internal("levels/levels.json").write(ObjectMapper)
//            return levels;
//        }
    }
}
