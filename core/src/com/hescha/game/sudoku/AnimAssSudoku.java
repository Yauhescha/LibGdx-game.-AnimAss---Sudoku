package com.hescha.game.sudoku;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.hescha.game.sudoku.screen.MainMenuScreen;

public class AnimAssSudoku extends Game {

    public static float WORLD_WIDTH = 720;
    public static float WORLD_HEIGHT = 1280;
    public static AnimAssSudoku launcher;
    public static Color BACKGROUND_COLOR =  new Color(242f/255,231f/255,216f/255,1);

    @Override
    public void create() {
        WORLD_WIDTH = Gdx.graphics.getWidth();
        WORLD_HEIGHT = Gdx.graphics.getHeight();
        launcher = this;
        setScreen(new MainMenuScreen());
    }
}
