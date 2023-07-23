package com.hescha.game.sudoku.screen;


import static com.hescha.game.sudoku.AnimAssSudoku.BACKGROUND_COLOR;
import static com.hescha.game.sudoku.AnimAssSudoku.WORLD_HEIGHT;
import static com.hescha.game.sudoku.AnimAssSudoku.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hescha.game.sudoku.AnimAssSudoku;
import com.hescha.game.sudoku.util.FontUtil;


public class MainMenuScreen extends ScreenAdapter {
    public static MainMenuScreen screen;
    private Stage stage;
    private BitmapFont font;
    private Viewport viewport;

    @Override
    public void show() {
        screen = this;
        OrthographicCamera camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply(true);
        font = FontUtil.generateFont(Color.BLACK);

        Table table = new Table();
        table.setFillParent(true);
        stage = new Stage(viewport);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        Texture buttonTexture = new Texture(Gdx.files.internal("ui/button.png"));
        Texture headerTexture = new Texture(Gdx.files.internal("ui/header.png"));
        Texture mainImage = new Texture(Gdx.files.internal("ui/MainImage.png"));

        TextureRegion headerCover = new TextureRegion(headerTexture);
        TextureRegionDrawable buttonDrawable0 = new TextureRegionDrawable(headerCover);
        ImageTextButton imageTextButton0 = new ImageTextButton("PUZZLE", new ImageTextButton.ImageTextButtonStyle(buttonDrawable0, null, null, font));
        table.add(imageTextButton0).center().padTop(10).padBottom(10).row();

        Image mainimage = new Image(mainImage);
        table.add(mainimage).center().fillX().padTop(10).padBottom(10).row();

        TextureRegion btnPlay = new TextureRegion(buttonTexture);
        TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(btnPlay);
        ImageTextButton imageTextButton1 = new ImageTextButton("PLAY", new ImageTextButton.ImageTextButtonStyle(buttonDrawable1, null, null, font));
        table.add(imageTextButton1).center().padTop(10).padBottom(10).row();
        imageTextButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AnimAssSudoku.launcher.setScreen(new SelectDifficultyScreen(false));
            }
        });

        TextureRegion bthGallery = new TextureRegion(buttonTexture);
        TextureRegionDrawable buttonDrawable2 = new TextureRegionDrawable(bthGallery);
        ImageTextButton imageTextButton2 = new ImageTextButton("GALLERY", new ImageTextButton.ImageTextButtonStyle(buttonDrawable2, null, null, font));
        table.add(imageTextButton2).center().padTop(10).padBottom(10).row();
        imageTextButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AnimAssSudoku.launcher.setScreen(new SelectDifficultyScreen(true));
            }
        });

        TextureRegion bthExit = new TextureRegion(buttonTexture);
        TextureRegionDrawable buttonDrawable3 = new TextureRegionDrawable(bthExit);
        ImageTextButton imageTextButton3 = new ImageTextButton("EXIT", new ImageTextButton.ImageTextButtonStyle(buttonDrawable3, null, null, font));
        table.add(imageTextButton3).center().padTop(10).padBottom(10).row();
        imageTextButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND_COLOR);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        font.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
