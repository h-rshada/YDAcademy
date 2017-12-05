package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
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


}
