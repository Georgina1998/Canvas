package com.example.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Grid extends View {

    private final int gridColor;
    private Paint mPaint;
    private int cellsize;

    public Grid(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Grid, 0,0);

        try{
            gridColor = array.getInteger(R.styleable.Grid_background, 0);
        }finally {
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);
        int dimension = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        cellsize = dimension / 3;
        setMeasuredDimension(dimension, dimension);

    }

    @Override
    protected void onDraw(Canvas canvas){
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(16);
        mPaint.setColor(gridColor);
        mPaint.setAntiAlias(true);

        canvas.drawRect(0,0, getWidth(), getHeight(), mPaint);
        DrawGrid(canvas);
    }

    private void drawLine(){
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(16);
        mPaint.setColor(gridColor);
    }

    private void DrawGrid(Canvas canvas){
        for(int c = 0; c < 10; c++){
            drawLine();
            canvas.drawLine(cellsize*c, 0, cellsize*c, getWidth(), mPaint);
        }
        for(int r = 0; r < 10; r++){
            drawLine();
            canvas.drawLine(0, cellsize*r, getWidth(), cellsize*r, mPaint);
        }
    }


}
