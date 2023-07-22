package com.hescha.game.model;

import com.hescha.game.service.SudokuGenerator;

import lombok.Data;

@Data
public class Sudoku {
    private SudokuCell[][] board = new SudokuCell[SudokuGenerator.SIZE][SudokuGenerator.SIZE];
    private SudokuDifficulty sudokuDifficulty;
}
