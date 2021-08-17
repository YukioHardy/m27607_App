package com.example.m27607_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SudokuBoard sudokuBoard;
    private Solver sudokuSolver;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sudokuBoard = findViewById(R.id.Board);
        sudokuSolver = sudokuBoard.getSolver();
        text = findViewById(R.id.textView);
    }

    public void btnOnePress(View view) {
        sudokuSolver.setNumberPosition(1);
        sudokuBoard.invalidate();
    }

    public void btnTwoPress(View view) {
        sudokuSolver.setNumberPosition(2);
        sudokuBoard.invalidate();
    }

    public void btnThreePress(View view) {
        sudokuSolver.setNumberPosition(3);
        sudokuBoard.invalidate();
    }
    public void btnFourPress(View view) {
        sudokuSolver.setNumberPosition(4);
        sudokuBoard.invalidate();
    }
    public void btnFivePress(View view) {
        sudokuSolver.setNumberPosition(5);
        sudokuBoard.invalidate();
    }
    public void btnSixPress(View view) {
        sudokuSolver.setNumberPosition(6);
        sudokuBoard.invalidate();
    }
    public void btnSevenPress(View view) {
        sudokuSolver.setNumberPosition(7);
        sudokuBoard.invalidate();
    }
    public void btnEightPress(View view) {
        sudokuSolver.setNumberPosition(8);
        sudokuBoard.invalidate();
    }
    public void btnNinePress(View view) {
        sudokuSolver.setNumberPosition(9);
        sudokuBoard.invalidate();
    }

}