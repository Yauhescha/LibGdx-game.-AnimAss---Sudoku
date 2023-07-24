package com.hescha.game.sudoku.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SudokuDifficulty  implements Serializable {
    FOR_KIDS(1),
    EASY(30),
    NORMAL(40),
    HARDER(50),
    HARD(60);

    final int numberOfRemovedCells;
}
