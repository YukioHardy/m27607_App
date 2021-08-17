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

    public boolean completed() {
        for (int r=0; r<9; r++) {
            for (int c = 0; c < 9; c++) {
                if(this.board[r][c] == 0) return false;
            }
        }
        return true;
    }

    private boolean check(int i1, int j1, int i2, int j2) {
        boolean[] seen= new boolean[10];
        for (int i = 1; i <=9; i++) seen[i] = false;
        for (int i = i1; i < i2; i++) {
            for (int j = j1; j < j2; j++) {
                int value = board[i][j];
                if(value!=0) {
                    if (seen[value]) return false;
                    seen[value]= true;
                }
            }
        }
        return true;
    }

    //Test
    public boolean check() {
        for (int i = 0; i < 9; i++) {
            if(!check(i, 0, i+1, 9)) return false;
        }
        for (int j = 0; j < 9; j++) {
            if(!check(0, j, 9, j+1)) return false;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!check(3*i, 3*j, 3*i+3, 3*j+3))
                    return false;
            }
        }
        return true;
    }

    public void setNumberPosition(int num) {
        if(this.selected_row != -1 && this.selected_column != -1) {
            if(this.board[this.selected_row-1][this.selected_column-1] !=0) return;
            if(this.board[this.selected_row-1][this.selected_column-1] == num) {
                this.board[this.selected_row-1][this.selected_column-1] = 0;
            } else this.board[this.selected_row-1][this.selected_column-1] = num;
        }
    }

    public void deleteNumPosition() {
        if(this.selected_row != -1 && this.selected_column != -1) {
            this.board [this.selected_row-1][this.selected_column-1] = 0;
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
