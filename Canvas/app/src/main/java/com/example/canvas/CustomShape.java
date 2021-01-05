package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;

public class CustomShape extends View {

    private Rect rectangle;
    private Paint paint;


    public CustomShape(Context context) {
        super(context);
        int x = 50;
        int y = 50;
        int sideLength = 200;

        rectangle = new Rect(x, y, sideLength, sideLength);

        paint = new Paint();
        paint.setColor(Color.GRAY);
    }


    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawRect(rectangle, paint);
    }

}