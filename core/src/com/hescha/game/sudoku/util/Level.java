package com.hescha.game.sudoku.util;

import com.hescha.game.sudoku.model.Sudoku;
import com.hescha.game.sudoku.model.SudokuCell;
import com.hescha.game.sudoku.model.SudokuCellType;
import com.hescha.game.sudoku.screen.GalleryScreen;
import com.hescha.game.sudoku.screen.GameScreen;

import java.io.Serializable;

public class Level implements Serializable {
    private String imagePath;
    private Sudoku sudoku;
    private String category;
    private String name;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clearBoard() {
        for(SudokuCell[] cells: getSudoku().getBoard()){
            for (SudokuCell cell:cells){
                if(cell.getCellType()!= SudokuCellType.DISABLED)
                    cell.setNumber(0);
            }
        }
    }
}
