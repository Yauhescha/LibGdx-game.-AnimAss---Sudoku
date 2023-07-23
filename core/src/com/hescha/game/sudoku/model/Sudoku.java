package com.hescha.game.sudoku.model;

import com.hescha.game.sudoku.service.SudokuGenerator;

import lombok.Data;

@Data
public class Sudoku {
    private SudokuCell[][] board = new SudokuCell[SudokuGenerator.SIZE][SudokuGenerator.SIZE];
    private SudokuDifficulty sudokuDifficulty;
}
