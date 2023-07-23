package com.hescha.game.sudoku.model;

import lombok.Data;

@Data
public class SudokuCell {
    private SudokuCellType cellType;
    private int number;

    public SudokuCell(int number, SudokuCellType cellType) {
        this.number = number;
        this.cellType = cellType;
    }

    public boolean isDisabled() {
        return cellType == SudokuCellType.DISABLED;
    }
}
