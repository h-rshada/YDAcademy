package com.ydacademy.dell.Yashodeep2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SubscriptionPlan extends AppCompatActivity {
    @InjectView(R.id.btnBack)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_plan);
        ButterKnife.inject(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubscriptionPlan.this, DescriptionActivity.class);
                finish();
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SubscriptionPlan.this, DescriptionActivity.class);
        finish();
        startActivity(intent);

    }
}
