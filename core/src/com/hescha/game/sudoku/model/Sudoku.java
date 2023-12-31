package com.hescha.game.sudoku.model;

import java.io.Serializable;


public class Sudoku  implements Serializable {
    private SudokuCell[][] board = new SudokuCell[9][9];
    private SudokuDifficulty sudokuDifficulty;
    private SudokuCell selectedSell;

    public SudokuCell[][] getBoard() {
        return board;
    }

    public void setBoard(SudokuCell[][] board) {
        this.board = board;
    }

    public SudokuDifficulty getSudokuDifficulty() {
        return sudokuDifficulty;
    }

    public void setSudokuDifficulty(SudokuDifficulty sudokuDifficulty) {
        this.sudokuDifficulty = sudokuDifficulty;
    }

    public SudokuCell getSelectedSell() {
        return selectedSell;
    }

    public void setSelectedSell(SudokuCell selectedSell) {
        this.selectedSell = selectedSell;
    }

    public boolean isFilled() {
        for (SudokuCell[] sudokuCells : board) {
            for (SudokuCell sudokuCell : sudokuCells) {
                if(sudokuCell.getNumber()<=0)return false;
            }
        }
        return true;
    }
}
