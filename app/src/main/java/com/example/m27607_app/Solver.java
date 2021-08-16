package com.example.m27607_app;

public class Solver {
    int[][] board;
    int selected_row;
    int selected_column;

    Solver() {
        selected_row = -1;
        selected_column = -1;

        board = new int[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                board[r][c] = 0;
            }
        }
    }

    public int[][] getBoard() {
        return this.board;
    }

    public int getSelectedRow() {
        return selected_row;
    }

    public void setSelectedRow(int r) {
        selected_row = r;
    }

    public int getSelectedColumn() {
        return selected_column;
    }

    public void setSelectedColumn(int c) {
        selected_column = c;
    }
}