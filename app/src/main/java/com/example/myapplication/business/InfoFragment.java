package com.example.myapplication.business;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class InfoFragment extends Fragment {
    private TextView mTvAuthResult;
    private TextView mTvPayStatus;
    private TextView mTvContractCourse;
    private TextView mTvLcusNo;

    public static InfoFragment newInstance(String auth_result, String pay_status, String contract_course, String lcus_no) {
        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("auth_result", auth_result);
        bundle.putString("pay_status", pay_status);
        bundle.putString("contract_course", contract_course);
        bundle.putString("lcus_no", lcus_no);
        fragment.setArguments(bundle);
        return fragment;
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = null;
//        Log.d("InfoFragment", "---- onCreateView ----");
//
//        Fragment fragment = getFragmentManager().findFragmentByTag("info");
//        if (fragment != null) {
//            view = inflater.inflate(R.layout.fragment_info, container, false);
//            mTvAuthResult = (TextView) view.findViewById(R.id.v_authResult);
//            mTvPayStatus = (TextView) view.findViewById(R.id.v_payStatus);
//            mTvContractCourse = (TextView) view.findViewById(R.id.v_contractCourse);
//            mTvLcusNo = (TextView) view.findViewById(R.id.v_lcusNo);
//            Bundle bundle = fragment.getArguments();
//            mTvAuthResult.setText(bundle.getString("auth_result"));
//            mTvPayStatus.setText(bundle.getString("pay_status"));
//            mTvContractCourse.setText(bundle.getString("contract_course"));
//            mTvLcusNo.setText(bundle.getString("lcus_no"));
//        }
//        return view;
//    }
}
