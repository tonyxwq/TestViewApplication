package com.rx.testviewapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Author:XWQ
 * Time   2018/10/31
 * Descrition: this is CustomListViewAdapter
 */

public class CustomListViewAdapter extends ArrayAdapter<String>
{

    public CustomListViewAdapter(Context context, int textViewResourceId,
                                 List<String> objects)
    {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view;

       /* if (convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_listview_item, null);
        } else
        {
            view = convertView;
        }*/

        view = LayoutInflater.from(getContext()).inflate(
                R.layout.custom_listview_item, null);

        TextView contentTv = (TextView) view.findViewById(R.id.content_tv);
        contentTv.setText(getItem(position));

        return view;
    }

}
