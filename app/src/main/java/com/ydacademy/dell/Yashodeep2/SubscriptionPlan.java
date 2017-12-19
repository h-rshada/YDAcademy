package com.ydacademy.dell.Yashodeep2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SubscriptionPlan extends AppCompatActivity {
    @InjectView(R.id.btnBack)
    Button btnBack;
    @InjectView(R.id.btnCall)
    Button btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_plan);
        ButterKnife.inject(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubscriptionPlan.this, TabActivity.class);
                finish();
                startActivity(intent);

            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:9552827081"));
                if (ContextCompat.checkSelfPermission(SubscriptionPlan.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SubscriptionPlan.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(callIntent);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SubscriptionPlan.this, TabActivity.class);
        finish();
        startActivity(intent);

    }
    }

