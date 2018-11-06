package com.rx.testviewapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;


/**
 * Author:XWQ
 * Time   2018/10/31
 * Descrition: this is CustomListView
 */

public class CustomListView extends ListView implements View.OnTouchListener,
        GestureDetector.OnGestureListener
{

    // 手势动作探测器
    private GestureDetector mGestureDetector;

    // 删除事件监听器
    public interface OnDeleteListener
    {
        void onDelete(int index);
    }

    private OnDeleteListener mOnDeleteListener;

    // 删除按钮
    private View mDeleteBtn;

    // 列表项布局
    private ViewGroup mItemLayout;
    //临时项布局
    private ViewGroup mItemTempLayout;
    // 选择的列表项
    private int mSelectedItem;

    // 当前删除按钮是否显示出来了
    private boolean isDeleteShown;

    private int mAnimTime = 250;

    private long startTime, endTime;

    private boolean isFirstTouch = true;

    public CustomListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // 创建手势监听器对象
        mGestureDetector = new GestureDetector(getContext(), this);

        // 监听onTouch事件
        setOnTouchListener(this);
    }

    // 设置删除监听事件
    public void setOnDeleteListener(OnDeleteListener listener)
    {
        mOnDeleteListener = listener;
    }

    private float starty = 0, endy = 0;
    private float startx = 0, endx = 0;

    // 触摸监听事件
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                starty = event.getY();
                startTime = 0;
                endTime = 0;
                startTime = System.currentTimeMillis();
                Log.d("data", "=======startTime============" + startTime);
                break;
            case MotionEvent.ACTION_MOVE:
                endTime = System.currentTimeMillis();
                if (currentTime()) return false;
                endy = event.getY();
                if ((endy - starty) > 0 && (endy - starty) > 1 || (endy - starty) < 0 && (endy - starty) < 1)//向下滑动
                {
                    onDelete();
                }
                break;
            case MotionEvent.ACTION_UP:
                endTime = System.currentTimeMillis();
                if (currentTime())
                {
                    return false;
                }
                Log.d("data", "=======startTime============" + endTime);
                endy = event.getY();
                if ((endy - starty) > 0 && (endy - starty) > 1 || (endy - starty) < 0 && (endy - starty) < 1)//向下滑动
                {
                    onDelete();
                }
                break;
        }

        if (isFirstTouch)
        {
            isFirstTouch = false;
            return mGestureDetector.onTouchEvent(event);

        } else
        {
            if (currentTime())
            {
                return true;

            } else
            {
                return mGestureDetector.onTouchEvent(event);
            }
        }
    }

    private boolean currentTime()
    {
        if (endTime - startTime > 0 && endTime - startTime < 150)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e)
    {
        mSelectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY)
    {
        onFling(velocityX, velocityY);
        return false;
    }


    public synchronized void onDelete()
    {
        if (mDeleteBtn != null && mItemLayout != null)
        {
            temp = 0;
            temp1 = 0;
            ValueAnimatorTemp(160, mItemTempLayout);
            mItemTempLayout = null;
            mDeleteBtn = null;
        }
    }

    public synchronized void onFling(float velocityX, float velocityY)
    {
        // 如果当前删除按钮没有显示出来，并且x方向滑动的速度大于y方向的滑动速度
        if (Math.abs(velocityX) > Math.abs(velocityY))
        {
            mDeleteBtn = LayoutInflater.from(getContext()).inflate(
                    R.layout.delete_btn, null);

            mDeleteBtn.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    mOnDeleteListener.onDelete(mSelectedItem);
                    if (mDeleteBtn != null && mItemLayout != null)
                    {
                        onDelete();
                    }
                }
            });

            mItemLayout = (ViewGroup) getChildAt(mSelectedItem - getFirstVisiblePosition());
            mItemTempLayout = mItemLayout;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, 160);
            params.setMargins(0, 0, -180, 0);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            if (mItemLayout != null && mDeleteBtn != null)
            {
                mItemLayout.addView(mDeleteBtn, params);
                ValueAnimator(160, mItemLayout);
                isDeleteShown = true;
            }
        }
    }


    private int temp = 0;
    private int temp1 = 0;

    public synchronized void ValueAnimator(int index, final View view)
    {
        ValueAnimator anim = ValueAnimator.ofInt(0, index);
        // 设置动画运行的时长
        anim.setDuration(mAnimTime);
        anim.setRepeatCount(0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                int currentValue = (Integer) animation.getAnimatedValue();
                int currentmove = currentValue - temp;
                view.scrollBy(currentmove, 0);
                temp = currentValue;

            }
        });
        anim.start();
        // 启动动画
    }

    public synchronized void ValueAnimatorTemp(int index, final View view)
    {
        ValueAnimator anim = ValueAnimator.ofInt(0, index);
        // 设置动画运行的时长
        anim.setDuration(mAnimTime);
        anim.setRepeatCount(0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                int currentValue = (Integer) animation.getAnimatedValue();
                int currentmove = currentValue - temp1;
                view.scrollBy(-currentmove, 0);
                temp1 = currentValue;

            }
        });
        anim.start();
        // 启动动画
    }

    // 隐藏删除按钮
    public void hideDelete()
    {
        mItemLayout.removeView(mDeleteBtn);
        mDeleteBtn = null;
        isDeleteShown = false;
    }

    public boolean isDeleteShown()
    {
        return isDeleteShown;
    }

    /**
     * 后面几个方法本例中没有用到
     */
    @Override
    public void onShowPress(MotionEvent e)
    {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e)
    {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY)
    {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e)
    {

    }

}
