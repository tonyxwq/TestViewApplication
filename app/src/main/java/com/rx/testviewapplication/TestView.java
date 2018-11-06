package com.rx.testviewapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Author:XWQ
 * Time   2018/11/5
 * Descrition: this is TestView
 */

public class TestView extends View
{
    private int defaultWidth = 500;
    private int defaultHeight = 500;

    public TestView(Context context)
    {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

   /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = onMeasureDimesion(defaultWidth, widthMeasureSpec);
        int height = onMeasureDimesion(defaultHeight, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int onMeasureDimesion(int defaultsize, int measureSpec)
    {
        int size = defaultsize;
        int spacesize = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY)
        {
            size = spacesize;

        } else if (mode == MeasureSpec.AT_MOST)
        {
            size = Math.min(size, spacesize);
        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Log.d("data","=========onDraw==========");
        Log.d("data","=========onMeasure=========="+getWidth()+"============="+getHeight());
        Log.d("data","=========getMeasuredWidth()=========="+getMeasuredWidth()+"============="+getMeasuredHeight());
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        Log.d("data","=========onFinishInflate==========");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("data","=========onLayout==========");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("data","=========onSizeChanged==========");
    }
}
