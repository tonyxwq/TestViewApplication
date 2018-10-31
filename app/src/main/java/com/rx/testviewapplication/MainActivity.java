package com.rx.testviewapplication;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
    private com.rx.testviewapplication.CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleView = findViewById(R.id.circleview);
        circleView.ValueAnimator(360);
    }
}
