package com.rx.testviewapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Author:XWQ
 * Time   2018/10/30
 * Descrition: this is CircleView
 */

public class CircleView extends View
{
    private int circleColor;
    private int arcColor;
    private float textSize;
    private String text;
    private int textColor;
    private Integer startAngle;
    private Integer sweepAngle;

    private Paint mTextPaint;//文字画笔
    private Paint mCirclePaint;//内环画笔
    private Paint mArcPaint;//外环画笔
    private int width, height;

    private RectF mRectF;
    private RectF mRectFT;
    private RectF mRectFTT;
    private int mCircleXY;
    private int mmCircleY;
    private float mRadius;

    public CircleView(Context context)
    {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.circleView);
        if (typedArray != null)
        {
            arcColor = typedArray.getColor(R.styleable.circleView_arcColor, 100);
            textColor = typedArray.getColor(R.styleable.circleView_textColor, 0);
            text = typedArray.getString(R.styleable.circleView_text);
            textSize = typedArray.getDimension(R.styleable.circleView_textSize, 18);
            circleColor = typedArray.getColor(R.styleable.circleView_circleColor, 0);
            startAngle = typedArray.getInteger(R.styleable.circleView_startAngle, 0);
            sweepAngle = typedArray.getInteger(R.styleable.circleView_sweepAngle, 0);
            typedArray.recycle();
        }

        DisplayMetrics dm = getResources().getDisplayMetrics();
        height = dm.heightPixels;
        width = dm.widthPixels;
        int index = 50;
        int indexs = 100;
        int indexss = 150;
        int length = Math.min(width, height);
        mCircleXY = length / 2;
        mmCircleY = height / 2;
        mRadius = length * 0.5f / 4;
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(circleColor);
        mRectF = new RectF((width / 2 - mRadius) - index, (height / 2 - mRadius) - index, ((width / 2 - mRadius) + mRadius * 2 + index), ((height / 2 - mRadius) + mRadius * 2 + index));
        mRectFT = new RectF((width / 2 - mRadius) - indexs, (height / 2 - mRadius) - indexs, ((width / 2 - mRadius) + mRadius * 2 + indexs), ((height / 2 - mRadius) + mRadius * 2 + indexs));
        mRectFTT = new RectF((width / 2 - mRadius) - indexss, (height / 2 - mRadius) - indexss, ((width / 2 - mRadius) + mRadius * 2 + indexss), ((height / 2 - mRadius) + mRadius * 2 + indexss));
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setColor(arcColor);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth((30));
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleXY, mmCircleY, mRadius, mCirclePaint);
        canvas.drawArc(mRectFT, -90, pro1, false, mArcPaint);
        canvas.drawArc(mRectFTT, -90, pro2, false, mArcPaint);
        canvas.drawArc(mRectF, -90, pro3, false, mArcPaint);
        canvas.drawText(text, 0, text.length(), mCircleXY, mmCircleY + textSize / 4, mTextPaint);
    }

    int pro1, pro2, pro3;
    public void ValueAnimator(int index)
    {
        ValueAnimator anim = ValueAnimator.ofInt(0, index);
        // 设置动画运行的时长
        anim.setDuration(1000);
        anim.setRepeatCount(0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                int currentValue = (Integer) animation.getAnimatedValue();
                pro1 = currentValue;
                pro2 = currentValue;
                pro3 = currentValue;
                if (pro1 > 220)
                {
                    pro1 = 220;
                }
                if (pro2 > 320)
                {
                    pro2 = 320;
                }
                if (pro3 > 180)
                {
                    pro3 = 180;
                }
                invalidate();
            }
        });
        anim.start();
        // 启动动画
    }
}
