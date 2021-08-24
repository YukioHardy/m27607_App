package com.example.m27607_app;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolverUnitTest {
    Solver solver = new Solver();

    @Test
    public void numberPositionTest() {
        solver.setSelectedColumn(3);
        solver.setSelectedRow(3);
        int[][] board2 = new int[9][9];
        board2[2][2] = 4;
        solver.setNumberPosition(4);
        solver.setNumberPosition(5); //Testing, that number does not overwrite
        assertEquals(board2[2][2], solver.getBoard()[2][2]);
    }

    @Test
    public void SelectedRowTest() {
        solver.setSelectedRow(3);
        assertEquals(3, solver.getSelectedRow());
    }

    @Test
    public void SelectedColumnTest() {
        solver.setSelectedColumn(3);
        assertEquals(3, solver.getSelectedColumn());
    }

    @Test
    public void getBoardTest() {
        assertSame(solver.board, solver.getBoard());
    }

    @Test
    public void completed_test() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                solver.board[r][c] = 1;
            }
        }
        assertTrue(solver.completed());

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                solver.board[r][c] = 0;
            }
        }
        assertFalse(solver.completed());
    }

    @Test
    public void checkTest() {
        String correct =
                "4 3 5 2 6 9 7 8 1 " +
                        "6 8 2 5 7 1 4 9 3 " +
                        "1 9 7 8 3 4 5 6 2 " +
                        "8 2 6 1 9 5 3 4 7 " +
                        "3 7 4 6 8 2 9 1 5 " +
                        "9 5 1 7 4 3 6 2 8 " +
                        "5 1 9 3 2 6 8 7 4 " +
                        "2 4 8 9 5 7 1 3 6 " +
                        "7 6 3 4 1 8 2 5 9 ";
        String[] split = correct.split(" ");
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j < 9; j++) {
                String s = split[i * 9 + j];
                char c = s.charAt(0);
                solver.getBoard()[i][j] = c - '0';
            }
        }
        assertTrue(solver.check());

        String inCorrect =
                "4 3 5 2 6 9 7 8 1 " +
                        "6 8 2 5 7 1 4 9 3 " +
                        "1 9 6 8 3 4 5 6 2 " +
                        "8 2 6 1 9 5 3 4 7 " +
                        "3 7 4 6 8 2 9 1 5 " +
                        "9 5 1 7 4 3 6 2 8 " +
                        "5 1 9 3 2 6 8 7 4 " +
                        "2 4 8 9 5 7 1 3 6 " +
                        "7 6 3 4 1 8 2 5 9 ";
        String[] split2 = inCorrect.split(" ");
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j < 9; j++) {
                String s = split2[i * 9 + j];
                char c = s.charAt(0);
                solver.getBoard()[i][j] = c - '0';
            }
        }
        assertFalse(solver.check());
    }
}