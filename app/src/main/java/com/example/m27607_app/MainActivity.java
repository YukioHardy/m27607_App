package com.example.m27607_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Author: Rosalie Thost, m27607
 * Date: 18.08.2021
 * Task:
 *  Building an app with Android Studio
 *
 * This App simulates a classical Sudoku Game. There are 5 different puzzles to solve.
 * The puzzle can be solved by using the buttons below the Sudoku Grid.
 * If the "Check"-Button is clicked, the App will tell you if you solved the puzzle right.
 *
 * The MainActivity Class contains all onClick Methods for all the buttons and it calls the
 * activity_main Layout
 */
public class MainActivity extends AppCompatActivity {

    //Creating all needed Objects (A board, a Solver and a TextView
    private SudokuBoard sudokuBoard;
    private Solver sudokuSolver;
    private TextView text;

    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("TAG: " + TAG);
        //Log.d(TAG, "Loading methods");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sudokuBoard equals the Board on the screen (which is created in activity_main)
        sudokuBoard = findViewById(R.id.Board);
        //the sudokuSolver equals the Solver from our Board
        sudokuSolver = sudokuBoard.getSolver();
        //text is connected to the TextView in the layout
        text = findViewById(R.id.textView);
    }

    //Puts a 1 in the selected cell if you press the button
    //Updates the Board

    public void btnOnePress(View view) {
        //Log.d(TAG, "Putting Number 1 in Cell...");
        sudokuSolver.setNumberPosition(1);
        sudokuBoard.invalidate();
    }

    //Puts a 2 in the selected cell if you press the button
    //Updates the Board
    public void btnTwoPress(View view) {
        Log.d(TAG, "Putting Number 2 in Cell...");
        sudokuSolver.setNumberPosition(2);
        sudokuBoard.invalidate();
    }

    //Puts a 3 in the selected cell if you press the button
    //Updates the Board
    public void btnThreePress(View view) {
        Log.d(TAG, "Putting Number 3 in Cell...");
        sudokuSolver.setNumberPosition(3);
        sudokuBoard.invalidate();
    }

    //Puts a 4 in the selected cell if you press the button
    //Updates the Board
    public void btnFourPress(View view) {
        Log.d(TAG, "Putting Number 4 in Cell...");
        sudokuSolver.setNumberPosition(4);
        sudokuBoard.invalidate();
    }

    //Puts a 5 in the selected cell if you press the button
    //Updates the Board
    public void btnFivePress(View view) {
        Log.d(TAG, "Putting Number 5 in Cell...");
        sudokuSolver.setNumberPosition(5);
        sudokuBoard.invalidate();
    }

    //Puts a 6 in the selected cell if you press the button
    //Updates the Board
    public void btnSixPress(View view) {
        Log.d(TAG, "Putting Number 6 in Cell...");
        sudokuSolver.setNumberPosition(6);
        sudokuBoard.invalidate();
    }

    //Puts a 7 in the selected cell if you press the button
    //Updates the Board
    public void btnSevenPress(View view) {
        Log.d(TAG, "Putting Number 7 in Cell...");
        sudokuSolver.setNumberPosition(7);
        sudokuBoard.invalidate();
    }

    //Puts a 8 in the selected cell if you press the button
    //Updates the Board
    public void btnEightPress(View view) {
        Log.d(TAG, "Putting Number 8 in Cell...");
        sudokuSolver.setNumberPosition(8);
        sudokuBoard.invalidate();
    }

    //Puts a 9 in the selected cell if you press the button
    //Updates the Board
    public void btnNinePress(View view) {
        Log.d(TAG, "Putting Number 9 in Cell...");
        sudokuSolver.setNumberPosition(9);
        sudokuBoard.invalidate();
    }

    //Calls the restart Method
    //Resets the text that is shown on the screen
    //Updates the Sudoku Board
    public void btnRestart(View view) {
        Log.d(TAG, "Restarting board...");
        sudokuBoard.restart();
        Log.d(TAG, "Changing shown text...");
        text.setText(getString(R.string.Playing));
        sudokuBoard.invalidate();
    }

    //Calls the fillBoard Method, which will show a new Puzzle
    //Resets the text that is shown on the screen
    //Updates the Sudoku Board
    public void btnNew(View view) {
        Log.d(TAG, "Getting new game...");
        sudokuBoard.fillBoard();
        Log.d(TAG, "Changing shown text...");
        text.setText(getString(R.string.Playing));
        sudokuBoard.invalidate();
    }

    //Calls the delete Method
    //Deletes the number in the selected cell
    //Updates the Sudoku Board
    public void btnDelete(View view) {
        Log.d(TAG, "Deleting Number...");
        sudokuBoard.delete();
        sudokuBoard.invalidate();
    }

    //Checks, if all cells are filled with numbers by calling the completed Method
    //Calls the Check Method to see if the puzzle was solved right
    //Shows a message in the TextView based on the check Method
    //Updates the Board
    public void solve(View view) {
        Log.d(TAG, "Checking if completed...");
        if(sudokuSolver.completed()) {
            Log.d(TAG, "Checking if correct...");
            if(sudokuSolver.check()) {
                Log.d(TAG, "Changing shown text...");
                text.setText(getString(R.string.correct));
            } else {
                Log.d(TAG, "Changing shown text...");
                text.setText(getString(R.string.wrong));
            }
        } else {
            Log.d(TAG, "Changing shown text...");
            text.setText(getString(R.string.unfinsihed));
        }
        sudokuBoard.invalidate();
    }

}