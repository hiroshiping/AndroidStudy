package com.example.myapplication.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.myapplication.R;

public class ContainerActivity extends Activity implements AFragment.OnMessageClick {
    private AFragment aFragment;
    //    private BFragment bFragment;
//    private Button mBtnChange;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        mTvTitle = findViewById(R.id.tv_title);
//        mBtnChange  = (Button) findViewById(R.id.btn_change);
//        mBtnChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (bFragment == null){
//                    bFragment = new BFragment();
//                }
//                getFragmentManager().beginTransaction().add(R.id.fl_container,bFragment).commitAllowingStateLoss();
//            }
//        });

        aFragment = AFragment.newInstance("あいうえお");
        getFragmentManager().beginTransaction().add(R.id.fl_container, aFragment, "a").commitAllowingStateLoss();
    }

    public void setData(String title) {
        mTvTitle.setText(title);
    }

    @Override
    public void onClick(String text) {
        mTvTitle.setText(text);
    }
}