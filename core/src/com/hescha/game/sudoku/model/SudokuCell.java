package com.hescha.game.sudoku.model;

import static com.hescha.game.sudoku.AnimAssSudoku.WORLD_WIDTH;
import static com.hescha.game.sudoku.screen.GameScreen.bitmapFont;
import static com.hescha.game.sudoku.screen.GameScreen.glyphLayout;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hescha.game.sudoku.AnimAssSudoku;
import com.hescha.game.sudoku.screen.GameScreen;

import lombok.Data;

@Data
public class SudokuCell extends Actor {
    private Texture currentTexture;

    private SudokuCellType cellType;
    private int number = 0;
    private boolean isSelected;

    public SudokuCell() {
//        setSize(level.imageWidth, level.imageHeight);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SudokuCell[][] tiles = GameScreen.sudoku.getBoard();
                ;
                for (SudokuCell[] tile : tiles) {
                    for (SudokuCell tile1 : tile) {
                        tile1.setSelected(false);
                    }
                }
                setSelected(true);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (cellType == SudokuCellType.DISABLED) {
            batch.draw(AnimAssSudoku.texturePermanentCell, getX(), getY());
        } else if (isSelected) {
            batch.draw(AnimAssSudoku.textureSelectedCell, getX(), getY());
        } else if (number != 0) {
            batch.draw(AnimAssSudoku.textureFilledCell, getX(), getY());
        } else {
            batch.draw(AnimAssSudoku.textureEmptyCell, getX(), getY());
        }

        if (number != 0) {
            glyphLayout.setText(bitmapFont, number + "");
            bitmapFont.draw(batch, glyphLayout, (float) (getX() + getWidth() * 0.2), (float) (getY() + getHeight() * 0.8));
        }
    }

    public SudokuCell(int number, SudokuCellType cellType) {
        this.number = number;
        this.cellType = cellType;
    }

    public boolean isDisabled() {
        return cellType == SudokuCellType.DISABLED;
    }
}
