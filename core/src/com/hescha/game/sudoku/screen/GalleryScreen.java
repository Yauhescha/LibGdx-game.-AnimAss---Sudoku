package com.hescha.game.sudoku.screen;


import static com.hescha.game.sudoku.AnimAssSudoku.BACKGROUND_COLOR;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hescha.game.sudoku.AnimAssSudoku;
import com.hescha.game.sudoku.util.FontUtil;
import com.hescha.game.sudoku.util.Level;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GalleryScreen extends ScreenAdapter {
    private final Level level;
    private Viewport viewport;
    private Stage stageInfo;
    private BitmapFont bitmapFont;

    @Override
    public void show() {
        float worldWidth = 512;
        float worldHeight = 1280;
        OrthographicCamera camera = new OrthographicCamera(worldWidth, worldHeight);
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply(true);
        SpriteBatch batch = new SpriteBatch();

        bitmapFont = FontUtil.generateFont(Color.BLACK);

        stageInfo = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stageInfo);

        BitmapFont font = FontUtil.generateFont(Color.BLACK);

        Table table = new Table();
        stageInfo.addActor(table);
        Table innerTable = new Table();
        table.setFillParent(true);


        Texture mainImage = new Texture(Gdx.files.internal(level.getImagePath()));
        TextureRegion mainBoard = new TextureRegion(mainImage);
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(mainBoard);
        ImageTextButton imageTextButton = new ImageTextButton("", new ImageTextButton.ImageTextButtonStyle(buttonDrawable, null, null, font));
        innerTable.add(imageTextButton).top().row();


        Texture buttonTexture = new Texture(Gdx.files.internal("ui/button.png"));

        TextureRegion btnBack = new TextureRegion(buttonTexture);
        TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(btnBack);
        ImageTextButton imageTextButton1 = new ImageTextButton("Back", new ImageTextButton.ImageTextButtonStyle(buttonDrawable1, null, null, font));
        int imageHeight = 512;
        innerTable.add(imageTextButton1).center().padTop(10).padBottom(imageHeight).row();
        imageTextButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AnimAssSudoku.launcher.setScreen(SelectLevelScreen.screen);
            }
        });

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);
        Label emptyLabel1 = new Label(" ", labelStyle);
        innerTable.add(emptyLabel1);

        ScrollPane scrollPane = new ScrollPane(innerTable);
        table.add(scrollPane);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND_COLOR);

        stageInfo.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stageInfo.draw();
    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
        stageInfo.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
