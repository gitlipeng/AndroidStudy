package com.android.study.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.study.R;

/**
 * Created by user on 2015/10/21.
 */
public class HandWrite extends View{
    private Paint paint = null;
    private Bitmap originalBitmap = null;
    private Bitmap new1Bitmap = null;
    private Bitmap new2Bitmap = null;
    private float clickX = 0,clickY = 0;
    private float startX = 0,startY = 0;
    private boolean isMove = true;
    private boolean isClear = false;
    private int color = Color.GREEN;
    private float strokeWidth = 2.0f;

    public HandWrite(Context context, AttributeSet attrs) {
        super(context, attrs);
        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meinv).copy(Bitmap.Config.ARGB_8888, true);
        new1Bitmap = Bitmap.createBitmap(originalBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(handWriting(new1Bitmap),0,0,null);
    }

    public Bitmap handWriting(Bitmap originalBitmap){
        Canvas canvas = null;
        if(isClear){
            canvas = new Canvas(new2Bitmap);
        }else{
            canvas = new Canvas(originalBitmap);
        }

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        if(isMove){
            canvas.drawLine(startX,startY,clickX,clickY,paint);
        }

        startX = clickX;
        startY = clickY;

        if(isClear){
            return new2Bitmap;
        }
        return originalBitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        clickX = event.getX();
        clickY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isMove = false;
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                isMove = true;
                invalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }
}
