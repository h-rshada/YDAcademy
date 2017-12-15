package com.ydacademy.dell.Yashodeep2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class SubscriptionPlan extends AppCompatActivity {
    /*@InjectView(R.id.btnBack)
    Button btnBack;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_plan);
        ButterKnife.inject(this);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SubscriptionPlan.this, TabActivity.class);
        finish();
        startActivity(intent);

    }
    }

