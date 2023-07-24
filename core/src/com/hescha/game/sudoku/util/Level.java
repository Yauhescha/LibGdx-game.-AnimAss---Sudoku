package com.hescha.game.sudoku.util;

import com.hescha.game.sudoku.model.Sudoku;

import lombok.Data;

@Data
public class Level {
    private String imagePath;
    private Sudoku sudoku;
    private String category;
    private String name;
}
