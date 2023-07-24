package com.hescha.game.sudoku.screen;


import static com.hescha.game.sudoku.AnimAssSudoku.BACKGROUND_COLOR;
import static com.hescha.game.sudoku.AnimAssSudoku.PREFERENCE_SAVING_PATH;
import static com.hescha.game.sudoku.AnimAssSudoku.WORLD_HEIGHT;
import static com.hescha.game.sudoku.AnimAssSudoku.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hescha.game.sudoku.AnimAssSudoku;
import com.hescha.game.sudoku.model.Sudoku;
import com.hescha.game.sudoku.model.SudokuCell;
import com.hescha.game.sudoku.model.SudokuCellType;
import com.hescha.game.sudoku.service.SudokuService;
import com.hescha.game.sudoku.util.FontUtil;
import com.hescha.game.sudoku.util.Level;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameScreen extends ScreenAdapter {
    public static final String RESULT_SAVED = "Result saved ";
    public static final String CHECK_IF_GAME_EDNDED = "CHECK if GAME Ended: ";
    public static final String CLICKED_BY_BUMBR = "CLICKED BY Number: ";
    public static Sudoku sudoku;
    public final Level level;
    private Viewport viewport;
    private Stage stageInfo;
    private String levelScoreSavingPath;
    public static GlyphLayout glyphLayout;
    Table tableContainer;
    Table tableCells;
    Table tableNumbers;
    private ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    TextureRegionDrawable[][] boardTextures = new TextureRegionDrawable[9][9];
    ImageTextButton[][] boardNumbers = new ImageTextButton[9][9];


    public Texture textureSelectedCell;
    public Texture textureEmptyCell;
    public Texture textureFilledCell;
    public Texture texturePermanentCell;

    public TextureRegionDrawable textureSelectedCellDrawable;
    public TextureRegionDrawable textureEmptyCellDrawable;
    public TextureRegionDrawable textureFilledCellDrawable;
    public TextureRegionDrawable texturePermanentCellDrawable;

    public static BitmapFont fontWhite;
    public static BitmapFont fontBlack;

    private float elapsedTime;
    private float minTime;
    ImageTextButton infoLabel;
    boolean isSolved = false;

    @Override
    public void show() {
        glyphLayout = new GlyphLayout();
        shapeRenderer = new ShapeRenderer();


        fontWhite = FontUtil.generateFont(Color.WHITE);
        fontBlack = FontUtil.generateFont(Color.BLACK);

        textureEmptyCell = new Texture(Gdx.files.internal("ui/textureEmptyCell.png"));
        textureSelectedCell = new Texture(Gdx.files.internal("ui/textureSelectedCell.png"));
        textureFilledCell = new Texture(Gdx.files.internal("ui/textureFilledCell.png"));
        texturePermanentCell = new Texture(Gdx.files.internal("ui/texturePermanentCell.png"));


        textureSelectedCellDrawable = new TextureRegionDrawable(textureSelectedCell);
        textureEmptyCellDrawable = new TextureRegionDrawable(textureEmptyCell);
        textureFilledCellDrawable = new TextureRegionDrawable(textureFilledCell);
        texturePermanentCellDrawable = new TextureRegionDrawable(texturePermanentCell);

        Label.LabelStyle labelStyle = new Label.LabelStyle(fontBlack, Color.BLACK);

        float worldWidth = WORLD_WIDTH;
        float worldHeight = WORLD_HEIGHT;
        OrthographicCamera camera = new OrthographicCamera(worldWidth, worldHeight);
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply(true);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

        sudoku = level.getSudoku();
        stageInfo = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stageInfo);

        tableContainer = new Table();
        tableContainer.setFillParent(true);
        stageInfo.addActor(tableContainer);

        Label emptyLabel1 = new Label(" ", labelStyle);
        tableContainer.add(emptyLabel1).row();


        Texture texture = AnimAssSudoku.assetManager.get("ui/button.png", Texture.class);
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(textureRegion);
        infoLabel = new ImageTextButton("INFO", new ImageTextButton.ImageTextButtonStyle(buttonDrawable, null, null, fontWhite));
        tableContainer.add(infoLabel).expandX().fillX().padTop(10).padBottom(20).row();

        tableContainer.add(loadSudokuBoard()).center().row();
        tableContainer.add(loadNumbersForFilling()).center().row();

        Texture buttonTexture = AnimAssSudoku.assetManager.get("ui/button.png", Texture.class);
        TextureRegion btnBack = new TextureRegion(buttonTexture);
        TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(btnBack);
        ImageTextButton imageTextButton1 = new ImageTextButton("Back", new ImageTextButton.ImageTextButtonStyle(buttonDrawable1, null, null, fontWhite));
        tableContainer.add(imageTextButton1).center().padTop(10).row();
        imageTextButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AnimAssSudoku.launcher.setScreen(SelectLevelScreen.screen);
            }
        });


        levelScoreSavingPath = level.getSudoku().getSudokuDifficulty().name() + "-" + level.getCategory() + "-" + level.getName();
        Preferences prefs = Gdx.app.getPreferences(PREFERENCE_SAVING_PATH);
        minTime = prefs.getInteger(levelScoreSavingPath, 9999);
    }

    private Table loadSudokuBoard() {
        tableCells = new Table();
        SudokuCell[][] tiles = sudoku.getBoard();
        int pad = 5;
        int padBottom = 20;
        float size = WORLD_WIDTH / 11;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuCell tile1 = tiles[i][j];
                TextureRegionDrawable regionDrawable = new TextureRegionDrawable(getCellTexture(tile1));
                ImageTextButton imageTextButton = new ImageTextButton(
                        tile1.getNumber() + "",
                        new ImageTextButton.ImageTextButtonStyle(regionDrawable, null, null, fontWhite));
                imageTextButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println(CLICKED_BY_BUMBR + tile1.getNumber());
                        GameScreen.sudoku.setSelectedSell(tile1);
                    }
                });

                boardTextures[i][j] = regionDrawable;
                boardNumbers[i][j] = imageTextButton;

                i++;
                j++;
                if (i % 3 == 0 && j % 3 == 0) {
                    tableCells.add(imageTextButton).size(size).pad(pad).padBottom(padBottom).padRight(padBottom);
                }
                if (i % 3 == 0 && j % 3 != 0) {
                    tableCells.add(imageTextButton).size(size).pad(pad).padBottom(padBottom).padRight(0);
                }
                if (i % 3 != 0 && j % 3 == 0) {
                    tableCells.add(imageTextButton).size(size).pad(pad).padBottom(0).padRight(padBottom);
                }
                if (i % 3 != 0 && j % 3 != 0) {
                    tableCells.add(imageTextButton).size(size).pad(pad).padBottom(0).padRight(0);
                }
                i--;
                j--;
            }
            tableCells.row();
        }

        // Верните таблицу для размещения в основной таблице
        return tableCells;
    }

    private Table loadNumbersForFilling() {
        tableNumbers = new Table();
        //print numbers
        float size = WORLD_WIDTH / 8;
        for (int i = 1; i <= 9; i++) {
            TextureRegion btnBack = new TextureRegion(AnimAssSudoku.assetManager.get("ui/fieldSelectionButton.png", Texture.class));
            TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(btnBack);
            ImageTextButton imageTextButton1 = new ImageTextButton(i + "", new ImageTextButton.ImageTextButtonStyle(buttonDrawable1, null, null, fontBlack));
            tableNumbers.add(imageTextButton1).size(size).pad(10);
            int finalI = i;
            imageTextButton1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!isSolved && sudoku.getSelectedSell() != null) {
                        sudoku.getSelectedSell().setNumber(finalI);
                    }
                }
            });
            if (i == 5) {
                tableNumbers.row();
            }
        }

        //print clear button
        TextureRegion btnClean = new TextureRegion(AnimAssSudoku.assetManager.get("ui/cleanIcon.png", Texture.class));
        TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(btnClean);
        ImageTextButton imageTextButton1 = new ImageTextButton(" ", new ImageTextButton.ImageTextButtonStyle(buttonDrawable1, null, null, fontBlack));
        tableNumbers.add(imageTextButton1).size(size).pad(10);
        imageTextButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!isSolved && sudoku.getSelectedSell() != null) {
                    sudoku.getSelectedSell().setNumber(0);
                }
            }
        });
        tableNumbers.row();

        return tableNumbers;
    }


    @Override
    public void render(float delta) {
        if (!isSolved) {
            updatePuzzleStatus();
            elapsedTime += Gdx.graphics.getDeltaTime();
        }
        infoLabel.setText("Seconds: " + (int) elapsedTime + "\n" +
                "Seconds min: " + (int) minTime);

        ScreenUtils.clear(BACKGROUND_COLOR);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuCell tile = sudoku.getBoard()[i][j];
                boardTextures[i][j].setRegion(getCellTexture(tile));
                boardNumbers[i][j].setText(tile.getNumber() + "");
            }
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        float x = tableCells.getX(); // X-координата верхнего левого угла квадрата
        float y = tableCells.bottom().getY(); // Y-координата верхнего левого угла квадрата
        shapeRenderer.rect(x, y, tableCells.getWidth(), tableCells.getMinHeight());
        shapeRenderer.end();

        stageInfo.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stageInfo.draw();
    }


    public TextureRegion getCellTexture(SudokuCell cell) {
        if (cell.getCellType() == SudokuCellType.DISABLED) {
            return new TextureRegion(texturePermanentCell);
        } else if (GameScreen.sudoku.getSelectedSell() == cell) {
            return new TextureRegion(textureSelectedCell);
        } else if (cell.getNumber() != 0) {
            return new TextureRegion(textureFilledCell);
        } else {
            return new TextureRegion(textureEmptyCell);
        }
    }

    private void updatePuzzleStatus() {
        isSolved = SudokuService.isRowSolvedCorrect(sudoku);
        System.out.println(CHECK_IF_GAME_EDNDED + isSolved);
        if (isSolved && elapsedTime < minTime) {
            saveBestResult();
        }
    }

    private void saveBestResult() {
        minTime = elapsedTime;
        Preferences prefs = Gdx.app.getPreferences(PREFERENCE_SAVING_PATH);
        prefs.putInteger(levelScoreSavingPath, (int) minTime);
        prefs.flush();
        System.out.println(RESULT_SAVED + minTime);
    }

    @Override
    public void dispose() {
        stageInfo.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
