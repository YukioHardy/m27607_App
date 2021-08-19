package com.example.m27607_app;


import android.util.Log;

/**
 * The Solver Method contains the Methods for checking the awnsers and positioning the numbers
 * on the Board
 */
public class Solver {
    //Creating variables for a row, a column and a board
    int[][] board;
    int selected_row;
    int selected_column;

    private static final String TAG = Solver.class.getSimpleName();
    Solver() {
        System.out.println("TAG: " + TAG);
        Log.d(TAG, "Loading methods");
        //Setting row and collumn to -1, so that there is no selected cell when starting the game
        selected_row = -1;
        selected_column = -1;

        //Sets the board to a 9x9 Array
        board = new int[9][9];

        //Puts 0 in all cells of the Array
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                board[r][c] = 0;
            }
        }
    }

    //Checks, if all cells are filled with a number
    //If there is a missing number, it returns false
    //Otherwise it returns true
    public boolean completed() {
        Log.d(TAG, "Checking if competed...");
        for (int r=0; r<9; r++) {
            for (int c = 0; c < 9; c++) {
                if(this.board[r][c] == 0) return false;
            }
        }
        return true;
    }

    private boolean check(int i1, int j1, int i2, int j2) {
        Log.d(TAG, "Checking if numbers are repeating...");
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

    //Checks, if there are the same numbers with the check-Method
    //If everything is right, it returns true
    public boolean check() {
        //Checking the Rows for equal numbers
        Log.d(TAG, "Checking rows...");
        for (int i = 0; i < 9; i++) {
            if(!check(i, 0, i+1, 9)) return false;
        }
        //Checking the columns for equal numbers
        Log.d(TAG, "Checking columns...");
        for (int j = 0; j < 9; j++) {
            if(!check(0, j, 9, j+1)) return false;
        }
        //Checking the small 3x3 Squeres for equal numbers
        Log.d(TAG, "Checking squares...");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!check(3*i, 3*j, 3*i+3, 3*j+3))
                    return false;
            }
        }
        return true;
    }

    //Sets the numbers at the selected Cell, as long as the selected Cell (Row and Column) is
    //on the Board
    //Numbers can't be placed when there is already a number in the Cell other than 0
    public void setNumberPosition(int num) {
        Log.d(TAG, "Setting numbers on board...");
        if(this.selected_row != -1 && this.selected_column != -1) {
            if(this.board[this.selected_row-1][this.selected_column-1] !=0) return;
            if(this.board[this.selected_row-1][this.selected_column-1] == num) {
                this.board[this.selected_row-1][this.selected_column-1] = 0;
            } else this.board[this.selected_row-1][this.selected_column-1] = num;
        }
    }

    //Getter and Setter for the Row and Column
    //Getter for the Board
    public int[][] getBoard() {
        Log.d(TAG, "Returning board...");
        return this.board;
    }

    public int getSelectedRow() {
        Log.d(TAG, "Returning selected row...");
        return selected_row;
    }

    public void setSelectedRow(int r) {
        Log.d(TAG, "Setting selected row...");
        selected_row = r;
    }

    public int getSelectedColumn() {
        Log.d(TAG, "Returning selected column...");
        return selected_column;
    }

    public void setSelectedColumn(int c) {
        Log.d(TAG, "Setting selected column...");
        selected_column = c;
    }
}
