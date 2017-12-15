package com.ydacademy.dell.Yashodeep2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        ButterKnife.inject(this);
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
                intent = new Intent(Guest.this, TabActivity.class);
                editor.putString("CLASS1", class1);
                editor.commit();
                startActivity(intent);
            }
        });
        btnTwelth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class1 = btnTwelth.getText().toString();
                intent = new Intent(Guest.this, TabActivity.class);
                editor.putString("CLASS1", class1);
                editor.commit();
                startActivity(intent);
            }
        });

    }
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
