package com.hescha.game.sudoku.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Sudoku  implements Serializable {
    private SudokuCell[][] board = new SudokuCell[9][9];
    private SudokuDifficulty sudokuDifficulty;
    private SudokuCell selectedSell;
}
