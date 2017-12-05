package com.example.user.ydacademy;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;


public class AboutUs extends AppCompatActivity {

    @InjectView(R.id.text)
    TextView textView;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ButterKnife.inject(this);
        // Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_up);
        //textView.setAnimation(animation);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textView.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }


    }

}
