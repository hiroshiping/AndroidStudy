package com.example.myapplication.business;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.R;
import com.example.myapplication.common.MyApplication;

public class GridFragment extends Fragment {
    private Context context = getContext();

    public static GridFragment newInstance() {
        GridFragment fragment = new GridFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        int[] images = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,};
        String[] text = getPicText();

        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter(context, images, text));

        return view;
    }

    private static class MyAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private int[] images;
        private String[] text;

        public MyAdapter(Context context, int[] images, String[] text) {
            this.images = images;
            this.text = text;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = layoutInflater.inflate(R.layout.item_grideview_layout, null);
            ImageView iv = (ImageView) v.findViewById(R.id.iv_gridView_item);
            TextView tv = (TextView) v.findViewById(R.id.tv_gridView_item);
            iv.setImageResource(images[position]);
            tv.setText(text[position]);
            return v;
        }
    }

    public Context getContext() {
        return MyApplication.getInstance();
    }

    private String[] getPicText() {
        String[] picText = new String[15];
        for (int i = 0; i < picText.length; i++) {
            picText[i] = "ピクチャー" + i;
        }
        return picText;
    }
}