package com.example.myapplication.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import androidx.annotation.NonNull;

public class PopUpAlertDialog extends Dialog {

    private Context mContext;
    private View popupView;
    private ImageView ivContent;
    private ImageButton ibClose;
    private ImageView ivConfirm;

    public PopUpAlertDialog(@NonNull Context context) {
        super(context, R.style.PopupDialog);
        mContext = context;
        popupView = LayoutInflater.from(context)
                .inflate(R.layout.alert_layout, null);
        ivContent = popupView.findViewById(R.id.iv_content);
        ivConfirm = popupView.findViewById(R.id.iv_confirm);
        popupView.setBackgroundResource(android.R.color.transparent);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        double size = (isTablet(context.getApplicationContext()) ? 0.6 : 0.75);
        int width = (int) (dm.widthPixels * size);
        int height = (int) (width * 1.4);
        int imgHeight = (int) ((height-30*dm.density) * 10 / 12);
        int btnHeight = (int) (height * 2 / 12);

        ivContent.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, imgHeight));
        ivConfirm.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, btnHeight));
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
        lp.gravity = Gravity.CENTER;
        setContentView(popupView, lp);
        setCancelable(false);
    }

    public void initVew(String urlButton){
//        Glide.with(popupView)
//                .load(urlButton)
//                .into(((ImageView)popupView.findViewById(R.id.iv_confirm)));
        popupView.findViewById(R.id.ib_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private boolean isTablet(Context context){
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        float width = display.widthPixels;
        float height = display.heightPixels;
        double x = Math.pow(width/display.xdpi, 2);
        double y = Math.pow(height/display.ydpi, 2);
        double inch = Math.sqrt(x + y);
        Log.d(getClass().getSimpleName(), "the screen is inch "+ inch);

        boolean isTablet = (context.getResources().getConfiguration().screenLayout& Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        Log.d(getClass().getSimpleName(), "the screen is tablet "+ isTablet);
        if(isTablet || inch > 6){
            return true;
        }
        return false;
    }
}
