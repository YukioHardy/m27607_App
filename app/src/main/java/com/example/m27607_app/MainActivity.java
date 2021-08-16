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

}