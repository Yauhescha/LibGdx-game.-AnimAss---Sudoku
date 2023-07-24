package com.hescha.game.sudoku;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.hescha.game.sudoku.model.SudokuDifficulty;
import com.hescha.game.sudoku.screen.GameScreen;
import com.hescha.game.sudoku.service.SudokuGenerator;
import com.hescha.game.sudoku.util.FontUtil;
import com.hescha.game.sudoku.util.Level;

public class AnimAssSudoku extends Game {
    public static final String PREFERENCE_SAVING_PATH = "AnimAss_Sudoku";

    public static float WORLD_WIDTH = 720;
    public static float WORLD_HEIGHT = 1280;
    public static AnimAssSudoku launcher;
    public static Color BACKGROUND_COLOR =  new Color(242f/255,231f/255,216f/255,1);



    public static Texture fieldSelectionButton;
    public static Texture cleanIcon;

    @Override
    public void create() {
        WORLD_WIDTH = Gdx.graphics.getWidth();
        WORLD_HEIGHT = Gdx.graphics.getHeight();
        launcher = this;


        fieldSelectionButton = new Texture(Gdx.files.internal("ui/fieldSelectionButton.png"));
        cleanIcon = new Texture(Gdx.files.internal("ui/cleanIcon.png"));


        Level level = new Level();
        level.setCategory("Cat");
        level.setSudoku(new SudokuGenerator().generateGame(SudokuDifficulty.FOR_KIDS));
        level.setName("girst level");
        level.setImagePath("ui/button.png");
        setScreen(new GameScreen(level));
    }
}
