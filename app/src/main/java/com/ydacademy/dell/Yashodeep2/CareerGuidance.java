package com.ydacademy.dell.Yashodeep2;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;

public class CareerGuidance extends AppCompatActivity {
    TextView textView;
    @InjectView(R.id.img_back)
    ImageView imageback;

    Toolbar toolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_guidance);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();

        CareerGuidenceFragment careerGuidenceFragment = (CareerGuidenceFragment) fragmentManager.findFragmentById(R.id.frag1);


    }

    @OnClick(R.id.img_back) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                Intent intent1 = new Intent(CareerGuidance.this, MainActivity.class);
                startActivity(intent1);
                break;
        }
    }

  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(CareerGuidance.this,MainActivity.class);
        startActivity(intent);
    }*/
}
