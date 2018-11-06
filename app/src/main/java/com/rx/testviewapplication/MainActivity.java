package com.rx.testviewapplication;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
    private com.rx.testviewapplication.CircleView circleView;
    // 自定义Lv
    private CustomListView mCustomLv;
    // 自定义适配器
    private CustomListViewAdapter mAdapter;
    // 内容列表
    private List<String> contentList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);
        //circleView = findViewById(R.id.circleview);
        // circleView.ValueAnimator(360);
        /*initContentList();
        mCustomLv = findViewById(R.id.custom_lv);
        mCustomLv.setOnDeleteListener(new CustomListView.OnDeleteListener()
        {

            @Override
            public void onDelete(int index)
            {
                Toast.makeText(MainActivity.this,"点击删除了"+index,Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter = new CustomListViewAdapter(this, 0, contentList);
        mCustomLv.setAdapter(mAdapter);*/
    }

    // 初始化内容列表
    private void initContentList()
    {
        for (int i = 0; i < 20; i++)
        {
            contentList.add("内容项" + i);
        }
    }

    @Override
    public void onBackPressed()
    {
       /* if (mCustomLv.isDeleteShown())
        {
            mCustomLv.hideDelete();
            return;
        }*/
        super.onBackPressed();
    }

}
