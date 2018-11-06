package com.rx.testviewapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Author:XWQ
 * Time   2018/11/5
 * Descrition: this is CustomViewGroup
 */

public class CustomViewGroup extends ViewGroup
{
    private Scroller scroller;

    public CustomViewGroup(Context context)
    {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int mViewGroupWidth = getMeasuredWidth(); //当前ViewGroup的总宽度
        int mPainterPosX = l;//当前绘制光标X坐标
        int mPainterPosY = t;//当前绘制光标Y坐标
        int childCount = getChildCount();//子控件的数量
        //遍历所有子控件，并在其位置上绘制子控件
        for (int i = 0; i < childCount; i++)
        {
            View childView = getChildAt(i);
            //子控件的宽和高
            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();

            CustomViewGroup.LayoutParams params = (CustomViewGroup.LayoutParams) childView
                    .getLayoutParams();//在onLayout中就可以获取子控件的mergin值了

            //如果剩余控件不够，则移到下一行开始位置
            if (mPainterPosX + width + params.leftMargin + params.rightMargin > mViewGroupWidth)
            {
                mPainterPosX = l;
                mPainterPosY += height + params.topMargin + params.bottomMargin;
            }
            //执行childView的绘制
            childView.layout(mPainterPosX + params.leftMargin, mPainterPosY + params.topMargin, mPainterPosX + params.leftMargin + width + params.rightMargin, mPainterPosY + height + params.topMargin + params.bottomMargin);
            //下一次绘制的X坐标
            mPainterPosX += width + params.leftMargin + params.rightMargin;
        }
    }

    //要使子控件的margin属性有效，必须定义静态内部类，继承ViewGroup.MarginLayoutParams
    public static class LayoutParams extends ViewGroup.MarginLayoutParams
    {

        public LayoutParams(Context c, AttributeSet attrs)
        {
            super(c, attrs);
        }
    }

    //要使子控件的margin属性有效，必须重写该函数，返回内部类实例
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new CustomViewGroup.LayoutParams(getContext(), attrs);
    }

    private float lastDownY;
    private float mScrollStart;
    private float mScrollEnd;

    float startY = -1;
    float temp = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                //this.scrollBy(0, (int)event.getY());

                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
