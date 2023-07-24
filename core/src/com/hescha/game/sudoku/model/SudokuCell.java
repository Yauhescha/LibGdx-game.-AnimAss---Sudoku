package com.hescha.game.sudoku.model;


import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SudokuCell implements Serializable {
    private SudokuCellType cellType;
    private int number;

    public SudokuCell(int number, SudokuCellType cellType) {
        this.number = number;
        this.cellType = cellType;
    }

    public void setNumber(int number) {
        if (cellType != SudokuCellType.DISABLED) {
            this.number = number;
        }
    }
}
