package com.example.m27607_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SudokuBoard extends View {
    private final int boardColor;
    private final int cellFillColor;
    private final int cellsHighlightColor;

    private final int letterColor;
    private final int letterColorStart;

    private final Paint boardColorPaint = new Paint();
    private final Paint cellFillColorPaint = new Paint();
    private final Paint cellsHighlightColorPaint = new Paint();

    private final Paint letterPaint = new Paint();
    private final Rect letterPaintBounds = new Rect();
    private int cellSize;

    ArrayList<ArrayList<Object>> startNumber;
    private int game = 0;

    private final Solver solver = new Solver();

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,
                0, 0);
        try {
            boardColor = a.getInteger(R.styleable.SudokuBoard_boardColor, 0);
            cellFillColor = a.getInteger(R.styleable.SudokuBoard_cellFillColor, 0);
            cellsHighlightColor = a.getInteger(R.styleable.SudokuBoard_cellsHighlightColor, 0);
            letterColor = a.getInteger(R.styleable.SudokuBoard_letterColor, 0);
            letterColorStart = a.getInteger(R.styleable.SudokuBoard_letterColorSolve, 0);
        } finally {
            a.recycle();
        }
        fillBoard();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int dimension = Math.min(width, height);

        cellSize = dimension/9;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(16);
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        cellFillColorPaint.setStyle(Paint.Style.FILL);
        cellFillColorPaint.setAntiAlias(true);
        cellFillColorPaint.setColor(cellFillColor);

        cellsHighlightColorPaint.setStyle(Paint.Style.FILL);
        cellsHighlightColorPaint.setAntiAlias(true);
        cellsHighlightColorPaint.setColor(cellsHighlightColor);

        letterPaint.setStyle(Paint.Style.FILL);
        letterPaint.setAntiAlias(true);
        letterPaint.setColor(letterColor);

        colorCell(canvas, solver.getSelectedRow(), solver.getSelectedColumn());
        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
        drawBoard(canvas);
        drawNumbers(canvas);
    }

    public void fillBoard() {
        game++;
        this.startNumber = new ArrayList<>();
        String sudoku;
        String sudoku1 =
                "5 3 ? ? 7 ? ? ? ? " +
                        "6 ? ? 1 9 5 ? ? ? " +
                        "? 9 8 ? ? ? ? 6 ? " +
                        "8 ? ? ? 6 ? ? ? 3 " +
                        "4 ? ? 8 ? 3 ? ? 1 " +
                        "7 ? ? ? 2 ? ? ? 6 " +
                        "? 6 ? ? ? ? 2 8 ? " +
                        "? ? ? 4 1 9 ? ? 5 " +
                        "? ? ? ? 8 ? ? 7 9 ";
        String sudoku2 =
                "? 3 ? 7 ? ? ? 2 ? " +
                        "6 ? ? ? ? ? ? 5 ? " +
                        "5 ? ? ? 4 9 ? ? 7 " +
                        "1 ? ? ? 5 8 ? ? 4 " +
                        "? ? ? ? ? 2 ? ? 6 " +
                        "? ? ? 1 ? ? 2 ? ? " +
                        "3 ? ? 5 ? 1 ? ? ? " +
                        "? ? 7 ? 3 ? ? ? 2 " +
                        "? 4 ? ? 6 ? 5 ? ? ";
        String sudoku3 =
                "? ? ? ? ? ? ? ? ? " +
                        "? ? ? ? ? 3 ? 8 5 " +
                        "? ? 1 ? 2 ? ? ? ? " +
                        "? ? ? 5 ? 7 ? ? ? " +
                        "? ? 4 ? ? ? 1 ? ? " +
                        "? 9 ? ? ? ? ? ? ? " +
                        "5 ? ? ? ? ? ? 7 3 " +
                        "? ? 2 ? 1 ? ? ? ? " +
                        "? ? ? ? 4 ? ? ? 9 ";
        String sudoku4 =
                "? 1 ? 4 ? 2 ? 5 ? " +
                        "5 ? ? ? ? ? ? ? 6 " +
                        "? ? ? 3 ? 1 ? ? ? " +
                        "7 ? 5 ? ? ? 4 ? 8 " +
                        "? ? ? ? ? ? ? ? ? " +
                        "2 ? 8 ? ? ? 5 ? 9 " +
                        "? ? ? 9 ? 6 ? ? ? " +
                        "6 ? ? ? ? ? ? ? 2 " +
                        "? 7 ? 1 ? 3 ? 3 ? ";
        String sudoku5 =
                "? ? 6 ? 4 ? ? 9 7 " +
                        "? 4 ? 7 3 ? ? 1 ? " +
                        "? 1 7 ? 9 2 ? 3 ? " +
                        "6 ? ? ? 7 ? ? 8 ? " +
                        "1 ? 5 ? 6 ? 9 ? 3 " +
                        "? 2 ? ? 1 ? ? ? 6 " +
                        "? 5 ? 9 8 ? 1 6 ? " +
                        "? 9 ? ? 5 6 ? 7 ? " +
                        "8 6 ? ? 2 ? 3 ? ? ";
        if(game ==1) sudoku = sudoku1;
        else if(game ==2) sudoku = sudoku2;
        else if(game ==3) sudoku = sudoku3;
        else if(game ==4) sudoku = sudoku4;
        else {
            sudoku = sudoku5;
            game = 0;
        }
        String[] split = sudoku.split(" ");

        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j < 9; j++) {
                String s = split[i*9+j];
                char c = s.charAt(0);
                if(c == '?') {
                    solver.getBoard()[i][j] = 0;
                } else solver.getBoard()[i][j] = c-'0';
                if(solver.getBoard()[i][j]!=0) {
                    this.startNumber.add(new ArrayList<>());
                    this.startNumber.get(this.startNumber.size()-1).add(i);
                    this.startNumber.get(this.startNumber.size()-1).add(j);
                }
            }
        }
    }

    //Test
    public void restart() {
        game--;
        fillBoard();
    }

    public void delete() {
        solver.deleteNumPosition();
    }



    private void drawThickLine() {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(10);
        boardColorPaint.setColor(boardColor);
    }

    private void drawThinLine() {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(4);
        boardColorPaint.setColor(boardColor);
    }

    private void drawBoard(Canvas canvas) {
        for (int c = 0; c < 10; c++) {
            if(c%3 == 0) {
                drawThickLine();
            }
            else drawThinLine();
            canvas.drawLine(cellSize*c, 0, cellSize*c,
                    getWidth(), boardColorPaint);
        }

        for (int r = 0; r < 10; r++) {
            if(r%3 == 0) {
                drawThickLine();
            }
            else drawThinLine();
            canvas.drawLine(0, cellSize*r, getWidth(),
                    cellSize*r, boardColorPaint);
        }
    }

    public Solver getSolver() {
        return this.solver;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isValid;
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN) {
            solver.setSelectedRow((int)Math.ceil(y/cellSize));
            solver.setSelectedColumn((int)Math.ceil(x/cellSize));
            isValid = true;
        } else isValid = false;

        return isValid;
    }

    private void drawNumbers(Canvas canvas) {
        letterPaint.setTextSize(cellSize);
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if(solver.getBoard()[r][c] != 0) {
                    String text = Integer.toString(solver.getBoard()[r][c]);
                    float width, height;

                    letterPaint.getTextBounds(text, 0, text.length(), letterPaintBounds);
                    width = letterPaint.measureText(text);
                    height = letterPaintBounds.height();

                    canvas.drawText(text, (c*cellSize)+((cellSize - width)/2),
                            (r*cellSize+cellSize) - ((cellSize - height)/2), letterPaint);
                }
            }
        }

        letterPaint.setColor(letterColorStart);
        for (ArrayList<Object> start : this.startNumber) {
            int r = (int)start.get(0);
            int c = (int)start.get(1);

            String text = Integer.toString(solver.getBoard()[r][c]);
            float width, height;

            letterPaint.getTextBounds(text, 0, text.length(), letterPaintBounds);
            width = letterPaint.measureText(text);
            height = letterPaintBounds.height();

            canvas.drawText(text, (c*cellSize)+((cellSize - width)/2),
                    (r*cellSize+cellSize) - ((cellSize - height)/2), letterPaint);
        }
    }

    private void colorCell(Canvas canvas, int r, int c) {
        if(solver.getSelectedColumn() != -1 && solver.getSelectedRow() != -1) {
            canvas.drawRect((c-1)*cellSize, 0, c*cellSize,
                    cellSize*9, cellsHighlightColorPaint);

            canvas.drawRect(0, (r-1)*cellSize, cellSize*9,
                    r*cellSize, cellsHighlightColorPaint);

            canvas.drawRect((c-1)*cellSize, (r-1)*cellSize, c*cellSize,
                    r*cellSize, cellsHighlightColorPaint);
        }
        invalidate();
    }
}