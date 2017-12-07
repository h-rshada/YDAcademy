package com.ydacademy.dell.Yashodeep2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;


public class AboutUs extends AppCompatActivity {

    @InjectView(R.id.text)
    TextView textView;
    @InjectView(R.id.img_back)
    ImageView imageback;

    Toolbar toolbar ;
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

    @OnClick({R.id.img_back}) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                Intent intent = new Intent(AboutUs.this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
