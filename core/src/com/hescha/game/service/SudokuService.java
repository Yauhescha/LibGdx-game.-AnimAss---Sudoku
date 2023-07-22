package com.hescha.game.service;

import com.hescha.game.model.Sudoku;
import com.hescha.game.model.SudokuCell;
import com.hescha.game.model.SudokuDifficulty;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class SudokuService {

    private final SudokuGenerator sudokuGenerator;

    public Sudoku generateGame(SudokuDifficulty sudokuDifficulty) {
        return sudokuGenerator.generateGame(sudokuDifficulty);
    }

    public boolean isRowSolvedCorrect(Sudoku sudoku) {
        SudokuCell[][] sudokuBoard = sudoku.getBoard();
        int[][] board = new int[sudokuBoard.length][sudokuBoard.length];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sudokuBoard[i][j].getNumber();
            }
        }
        return isRowSolvedCorrect(board);
    }


    private boolean isRowSolvedCorrect(int[][] board) {
        int size = board.length;
        for (int i = 0; i < size; i++) {

            boolean[] rowCheck = new boolean[size];
            boolean[] colCheck = new boolean[size];
            boolean[] boxCheck = new boolean[size];

            for (int j = 0; j < size; j++) {

                if (board[i][j] < 1 || board[i][j] > 9 || board[j][i] < 1 || board[j][i] > 9) {
                    return false;
                }

                rowCheck[board[i][j] - 1] = true;
                colCheck[board[j][i] - 1] = true;

                int boxRowOffset = (i / 3) * 3;
                int boxColOffset = (i % 3) * 3;

                if (boxCheck[board[boxRowOffset + j / 3][boxColOffset + j % 3] - 1]) {
                    return false;
                }

                boxCheck[board[boxRowOffset + j / 3][boxColOffset + j % 3] - 1] = true;
            }

            for (int j = 0; j < size; j++) {
                if (!rowCheck[j] || !colCheck[j] || !boxCheck[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
