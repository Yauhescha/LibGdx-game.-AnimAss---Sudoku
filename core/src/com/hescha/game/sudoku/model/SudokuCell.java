package com.hescha.game.sudoku.model;


import com.badlogic.gdx.graphics.Texture;

import lombok.Data;

@Data
public class SudokuCell {
    private Texture currentTexture;

    private SudokuCellType cellType;
    private int number;
    private boolean isSelected;

    public SudokuCell(int number, SudokuCellType cellType) {
        this.number = number;
        this.cellType = cellType;
    }

    public void setNumber(int number) {
        if (cellType != SudokuCellType.DISABLED)
            this.number = number;
    }
}
