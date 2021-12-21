package com.example.myapplication.business;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.ui.views.PopUpAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity {
    private InfoFragment infoFragment;
    private GridFragment gridFragment;
    private Browser1Fragment browser1Fragment;
    private Browser2Fragment browser2Fragment;
    private CustomDialogFragment customDialogFragment;

    private PopUpAlertDialog mEventPopup;

    private TextView first_text;
    private TextView second_text;
    private TextView third_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        ActionBar bar = getSupportActionBar();
//        List<String> list = new ArrayList<>();
//        list.add("Navi Menu 1");
//        list.add("Navi Menu 2");
//        list.add("Navi Menu 3");
//        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//        bar.setListNavigationCallbacks(
//                new ArrayAdapter<>(this,
//                        android.R.layout.simple_list_item_1,
//                        android.R.id.text1, list),
//                this::onNavigationItemSelected);

        first_text = (TextView) findViewById(R.id.first_text);
        first_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                browser1Fragment = Browser1Fragment.newInstance();
                getFragmentManager().beginTransaction().add(R.id.fl_container, browser1Fragment, "browser1").commitAllowingStateLoss();
            }
        });

        second_text = (TextView) findViewById(R.id.second_text);
        second_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                gridFragment = GridFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.fl_container, gridFragment).addToBackStack(null).commitAllowingStateLoss();
            }
        });

        third_text = (TextView) findViewById(R.id.third_text);
        third_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                browser2Fragment = Browser2Fragment.newInstance();
//                getFragmentManager().beginTransaction().add(R.id.fl_container, browser2Fragment, "browser2").commitAllowingStateLoss();

                Uri uri = Uri.parse("https://www.baidu.com");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });


        // タイトルを表示しないようにする
//        getActionBar().setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);


//        Bundle bundle = getIntent().getExtras();
//        String auth_result = bundle.getString("auth_result");
//        String pay_status = bundle.getString("pay_status");
//        String contract_course = bundle.getString("contract_course");
//        String lcus_no = bundle.getString("lcus_no");
//        infoFragment = InfoFragment.newInstance(auth_result, pay_status, contract_course, lcus_no);
//        getFragmentManager().beginTransaction().add(R.id.fl_container, infoFragment, "info").commitAllowingStateLoss();
        gridFragment = GridFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.fl_container, gridFragment, "grid").commitAllowingStateLoss();

//        https://blog.csdn.net/wurui8/article/details/48399311
//        FragmentManager fm = getSupportFragmentManager();
//        customDialogFragment.show(fm, "customDialog");

        if(mEventPopup == null) {
            mEventPopup = new PopUpAlertDialog(this);
            mEventPopup.initVew("https://cdn.l-tike.com/genre/photo/210326_LHP_syousai.jpg");
        }
        mEventPopup.show();
    }

    // List Navigation の一覧から項目を選択したら呼び出されるコールバック
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // ここで、どの項目が選択されたかを検知し、適切なイベント処理を行う
        // イベント処理が実行されたら、true を返すようにする
        Log.d("itemPosition:", String.valueOf(itemPosition));
        Log.d("itemId:", String.valueOf(itemId));
        return false;
    }

  }