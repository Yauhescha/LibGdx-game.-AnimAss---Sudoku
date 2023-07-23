package com.hescha.game.sudoku.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SudokuDifficulty {
    EASY(30),
    NORMAL(40),
    HARDER(50),
    HARD(60);

    final int numberOfRemovedCells;
}
