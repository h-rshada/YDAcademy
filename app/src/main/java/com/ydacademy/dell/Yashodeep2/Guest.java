package com.ydacademy.dell.Yashodeep2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Guest extends AppCompatActivity {
    SharedPreferences.Editor editor;
    // Button btnEleventh, btnTwelth, btnTenth;
    Intent intent;
    SharedPreferences sp;
    String class1;
    @InjectView(R.id.btnTenth)
    Button btnTenth;
    @InjectView(R.id.btnEleventh)
    Button btnEleventh;
    @InjectView(R.id.btnTwelth)
    Button btnTwelth;
    @InjectView(R.id.img_back)
    ImageView imageback;
    /*@InjectView(R.id.btn_director)
       AppCompatButton btn_director;*/
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        SharedPreferences sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        editor = sp.edit();
        btnTenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Guest.this, TenthActivity.class);
                startActivity(intent);
            }
        });
        btnEleventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class1 = btnEleventh.getText().toString();
                Log.d("check11 ", class1);
                intent = new Intent(Guest.this, TabActivity.class);
                editor.putString("CLASS1", "11");
                editor.commit();
                startActivity(intent);
            }
        });
        btnTwelth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class1 = btnTwelth.getText().toString();
                intent = new Intent(Guest.this, TabActivity.class);
                editor.putString("CLASS1", "12");
                editor.commit();
                startActivity(intent);
            }
        });

    }

    @OnClick(R.id.img_back) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(Guest.this,"***********8",Toast.LENGTH_SHORT).show();
        intent = new Intent(Guest.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data", true);
        setResult(RESULT_OK, intent);
        finish();
    }*/
        }
    }
}