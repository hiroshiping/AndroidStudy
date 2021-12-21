package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.business.CustomDialogFragment;
import com.example.myapplication.business.HomeActivity;
import com.example.myapplication.fragment.ContainerActivity;
import com.example.myapplication.jump.AActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.example.myapplication.common.RetrofitCall;
import com.example.myapplication.jump.BActivity;
import com.example.myapplication.service.InfoService;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.JsonObject;

import javax.annotation.Nonnull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.OkHttpClient;

public class MainActivity extends Activity {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private SharedPreferences sharedPreferences;
    private EditText et_mail;
    private EditText et_password;
    private Button bt_log;
    private Button bt_cancel;
    private Button bt_jump;
    private Button bt_fragment;
    private Button bt_firebase_test;
    private Button bt_firebase_event;
    private Button bt_hyouka;
    private CheckBox ch_rememberPass;

    private String SP_MAil = "sp_mail";
    private String SP_PASSWORD = "sp_password";
    private String SP_IS_REMEMBER_PSD = "sp_is_remember_psd";
    private boolean mIsChecked = false;

    private FirebaseAuth mAuth;

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        initUi();
//        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    private void initUi() {
        et_mail = (EditText) findViewById(R.id.et_mail);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_log = (Button) findViewById(R.id.bt_log);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);
        bt_jump = (Button) findViewById(R.id.bt_jump);
        bt_fragment = (Button) findViewById(R.id.bt_fragment);
        bt_firebase_test = (Button) findViewById(R.id.bt_firebase_test);
        bt_firebase_event = (Button) findViewById(R.id.bt_firebase_event);
        bt_hyouka = (Button) findViewById(R.id.bt_hyouka);
        ch_rememberPass = (CheckBox) findViewById(R.id.ch_rememberPass);

        et_mail.setText("heikou@ssd.forestsoft.jp");
        et_password.setText("phapple123");

        et_mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mIsChecked) {
                    if (sharedPreferences == null) {
                        sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(SP_MAil, et_mail.getText().toString());
                    editor.commit();
                }
            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mIsChecked) {
                    if (sharedPreferences == null) {
                        sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(SP_PASSWORD, et_password.getText().toString());
                    editor.commit();
                }
            }
        });

//        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
//        boolean isRemenber = sharedPreferences.getBoolean("remenberpass", false);
//        if (isRemenber) {
//            String name = sharedPreferences.getString("mail", "");
//            String pass = sharedPreferences.getString("password", "");
//            et_mail.setText(name);
//            et_password.setText(pass);
//            ch_rememberPass.setChecked(true);
//        }

        ch_rememberPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("===", "チェック状態:" + isChecked);
                mIsChecked = isChecked;
                if (isChecked) {
                    if (sharedPreferences == null) {
                        sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                    }

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(SP_IS_REMEMBER_PSD, isChecked);
                    editor.putString(SP_MAil, et_mail.getText().toString());
                    editor.putString(SP_PASSWORD, et_password.getText().toString());
                    editor.commit();
                } else {

                }
            }
        });

        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = et_mail.getText().toString();
                String password = et_password.getText().toString();
//                login(mail, password);
                login2(mail, password);
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_firebase_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> user = new HashMap<>();
                user.put("name", "Alan");
                user.put("password", "Mathison");
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("===", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@Nonnull Exception e) {
                                Log.w("===", "Error adding document", e);
                            }
                        });
            }
        });

        bt_firebase_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "123456");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "login");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            }
        });

        bt_hyouka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
//                ReviewManager manager = ReviewManagerFactory.create(context);
                ReviewManager manager = ReviewManagerFactory.create(context);
                manager.requestReviewFlow().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // We can get the ReviewInfo object
                        ReviewInfo reviewInfo = task.getResult();
                        Log.d("debug", reviewInfo.toString());
                        manager.launchReviewFlow(MainActivity.this, reviewInfo).addOnCompleteListener(task2 -> {
                            if(task2.isSuccessful()){
                                Log.d("debug", "successful!!!");
                            } else {
                                Log.d("debug", "fail!!!");
                            }

                        });
                    } else {
                        Log.d("debug", "fail!!!!!!!!!");
                        Log.d("debug", task.getException().toString());
                    }
                });
            }
        });

        setListeners();
    }

    private void initData() {
        if (sharedPreferences == null) {
            sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        et_mail.setText(sharedPreferences.getString(SP_MAil, ""));
        et_password.setText(sharedPreferences.getString(SP_PASSWORD, ""));

        mIsChecked = sharedPreferences.getBoolean(SP_IS_REMEMBER_PSD, false);
        ch_rememberPass.setChecked(mIsChecked);
    }

    private void login(String mail, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://private-1f91d-star10.apiary-mock.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InfoService requestSerives = retrofit.create(InfoService.class);
        Call<JsonObject> call = requestSerives.getString(mail, password);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("code", String.valueOf(response.code()));
                if (response != null) {
                    Log.e("===", "return:" + response.body().toString());
                    JsonObject response_data = response.body().getAsJsonObject("response");
                    Log.i("===", "TrackRecord " + response_data.toString());
                    String auth_result = response_data.get("auth_result").toString();
                    String pay_status = response_data.get("pay_status").toString();
                    String contract_course = response_data.get("contract_course").toString();
                    String lcus_no = response_data.get("lcus_no").toString();
                    Log.i("===", "auth_result:" + auth_result + ",pay_status:" + pay_status + ",contract_course:" + contract_course + ",lcus_no:" + lcus_no);

                    Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (ch_rememberPass.isChecked()) {
                        editor.putBoolean("remenberpass", true);
                        editor.putString("mail", mail);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }

                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("auth_result", "auth_result");
                    bundle.putString("pay_status", pay_status);
                    bundle.putString("contract_course", contract_course);
                    bundle.putString("lcus_no", lcus_no);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("===", "失败");
                Log.e("Throwable", t.toString());
            }
        });
    }

    private void login2(String mail, String password) {
        if ("".equals(mail) || "".equals(password)) {
            Toast.makeText(MainActivity.this, "ユーザー名とパスワードを入力してください。", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Map<String, Object> data = new HashMap<>();
                                data.put("name", mail);
                                data.put("password", password);
                                data.put("instance_id", user.getUid());
                                data.put("erroType", null);
                                data.put("erroTypeId", 0);
                                data.put("erroInfo", null);

                                Exception ex = new Exception();
                                String method = ex.getStackTrace()[0].getClassName();
                                data.put("className", method);

                                data.put("creatTime", getNow());
                                data.put("nextAction", null);

                                addFireDB("user", data);

                                Log.d("===", "signInWithEmail:success");
                                Toast.makeText(MainActivity.this, "signInWithEmail:success",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("===", "signInWithEmail:failure", task.getException());

                                FirebaseUser user = mAuth.getCurrentUser();
                                Map<String, Object> data = new HashMap<>();
                                data.put("name", mail);
                                data.put("password", password);
                                data.put("instance_id", user.getUid());
                                data.put("erroType", "ログインエラー");
                                data.put("erroTypeId", 1);
                                data.put("erroInfo", "ユーザー名とパスワードを正しく入力してください。");

                                Exception ex = new Exception();
                                String method = ex.getStackTrace()[0].getClassName();
                                data.put("className", method);

                                data.put("creatTime", getNow());
                                data.put("nextAction", "正しくログインしてください。");

                                addFireDB("user", data);

                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }

    private void setListeners() {
        OnClick onClick = new OnClick();
        bt_jump.setOnClickListener(onClick);
        bt_fragment.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.bt_jump:
                    intent = new Intent(MainActivity.this, AActivity.class);
                    break;
                case R.id.bt_fragment:
                    intent = new Intent(MainActivity.this, ContainerActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    private void reload() {
    }

    private void addFireDB(String table, Map<String, Object> data) {
        if ("".equals(table) || table == null) {
        } else {
            boolean flag = false;
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection(table)
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("===", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@Nonnull Exception e) {
                            Log.w("===", "Error adding document", e);
                        }
                    });
        }
    }

    private String getNow() {
        TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
        sdf.setTimeZone(tz);
        return sdf.format(new Date());
    }
}