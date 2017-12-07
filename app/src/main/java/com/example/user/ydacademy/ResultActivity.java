package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ResultActivity extends AppCompatActivity {
    Bundle b;
    @InjectView(R.id.btn_viewdescription)
    Button viewDescription;
    @InjectView(R.id.txt_Correct)
    TextView correct;
    @InjectView(R.id.txt_incorrect)
    TextView incorrect;
    @InjectView(R.id.txt_answer)
    TextView answered;
    @InjectView(R.id.txt_unanswer)
    TextView unanswered;
    @InjectView(R.id.txt_performance)
    TextView text_performance;
    @InjectView(R.id.iv_performance)ImageView performance;
    String name, userans, result, exam, subject, es, chapter, class1, date, time;
    int marks = 0, attained = 0;
    UrlRequest urlRequest;
    String id;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.inject(this);

        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1 = sp.getString("CLASS", null);
        exam = getIntent().getStringExtra("Exam");
        subject = getIntent().getStringExtra("Subject");
        es = getIntent().getStringExtra("ES");
        chapter = getIntent().getStringExtra("Chapter");
        id = sp.getString("ID", null);
        date = getIntent().getStringExtra("Date");
        time = getIntent().getStringExtra("Time");
        b = new Bundle();
        b = getIntent().getExtras();
        name = b.getString("data");
        String arr[] = name.split(" ");
        userans = arr[0];
        result = arr[1];
        actionBarSetup();
        Log.d("userans", arr[0].length() + "");
        Log.d("result", arr[1].length() + " " + result);

        for (int i = 0; i < result.length(); i++) {

            if (userans.charAt(i) == result.charAt(i)) {
                marks++;
            }
        }
        for (int i = 0; i < result.length(); i++) {
            if (userans.charAt(i) != 'E') {
                attained++;
            }
        }


        correct.setText(marks + "");
        correct.setTextColor(Color.BLACK);
        incorrect.setText(attained - marks + "");
        incorrect.setTextColor(Color.RED);
        answered.setText(attained + "");
        answered.setTextColor(Color.BLACK);
        unanswered.setText(50 - attained + "");
        unanswered.setTextColor(Color.RED);

      if (marks>=0&&marks<=20)
        {
            performance.setImageDrawable(getResources().getDrawable(R.drawable.poor));
           text_performance.setText("Poor performance");
           text_performance.setTextColor(Color.RED);
        }
        else if (marks > 20 && marks <= 30) {
            performance.setImageDrawable(getResources().getDrawable(R.drawable.average));
            text_performance.setText("Average performance");
            text_performance.setTextColor(Color.parseColor("#FFA500"));
      } else if (marks > 30 && marks <= 45)
        {
            performance.setImageDrawable(getResources().getDrawable(R.drawable.good));
            text_performance.setText("Good performance");
            text_performance.setTextColor(Color.parseColor("#f4511e"));
        } else if (marks > 45 && marks <= 50)
        {
            performance.setImageDrawable(getResources().getDrawable(R.drawable.excellent));
            text_performance.setText("Excellent performance");
            text_performance.setTextColor(Color.GREEN);
        }

        Date d = new Date();
        String arr1[] = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime()).toString().split(" ");
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(ResultActivity.this);
        Log.d("URL", "http://yashodeepacademy.co.in/admin/routes/updatestudentresult.php?student_id=" + id + "&examcode=" + es + chapter + "&score=" + marks + "-50" + "&date=" + arr1[0] + "&time=" + arr1[1]);
        urlRequest.setUrl("http://yashodeepacademy.co.in/admin/routes/updatestudentresult.php?student_id=" + id + "&examcode=" + es + chapter + "&score=" + marks + "-50" + "&date=" + arr1[0] + "&time=" + arr1[1]);
        urlRequest.getResponse(new ServerCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("Response", response);

            }
        });

        viewDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, DescriptionActivity.class);
                intent.putExtra("data", name);
                intent.putExtra("Class", class1);
                intent.putExtra("Subject", subject);
                intent.putExtra("Exam", exam);
                intent.putExtra("ES", es);
                intent.putExtra("Chapter", chapter);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ResultActivity.this, TabActivity.class));
        finish();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home/" + exam + "/" + subject);
        }
    }
}
