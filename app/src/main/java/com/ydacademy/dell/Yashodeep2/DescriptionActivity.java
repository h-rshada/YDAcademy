package com.ydacademy.dell.Yashodeep2;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DescriptionActivity extends AppCompatActivity {

    AdapterResult adapter;
    @InjectView(R.id.hostelList)
    RecyclerView recyclerView;
    List<DataResult> data;
    SharedPreferences sp;
    String class1, exam, subject, es, chapter, id, standard, user;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ButterKnife.inject(this);
        List<DataResult> data = new ArrayList<>();
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1 = sp.getString("CLASS", null);
        standard = sp.getString("CLASS1", null);
        Log.d("Class***", class1);
        Log.d("Standard***", standard);
        user = class1;
        Log.d("User***", user);
        if (user.equals("guest")) {
            class1 = standard;
        }
        Log.d("Class***", class1);
        exam = getIntent().getStringExtra("Exam");
        subject = getIntent().getStringExtra("Subject");
        es = getIntent().getStringExtra("ES");
        chapter = getIntent().getStringExtra("Chapter");
        id = sp.getString("ID", null);
        Log.d("class**", class1);
        Log.d("ES**", es);
        Log.d("chapter**", chapter);
        actionBarSetup();
        b = new Bundle();
        b = getIntent().getExtras();
        String name = b.getString("data");
        String arr[] = name.split(" ");

        Log.d("desc", arr[0]);

        for (int i = 0; i < 50; i++) {
            DataResult testData = new DataResult();
            testData.imageURL = "http://yashodeepacademy.co.in/admin/routes/" + class1 + "/" + es + chapter + "/q" + (i + 1) + ".PNG";
            testData.userans = arr[0].charAt(i);
            testData.result = arr[1].charAt(i);
            testData.description_url = "http://yashodeepacademy.co.in/admin/routes/" + class1 + "/" + es + chapter + "/a" + (i + 1) + ".PNG";
            data.add(testData);
        }

        recyclerView.setVisibility(View.VISIBLE);
        adapter = new AdapterResult(DescriptionActivity.this, data);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(DescriptionActivity.this);
        recyclerView.setLayoutManager(llm);
        adapter.notifyDataSetChanged();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home/" + exam + "/" + subject);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        Intent intent = new Intent(DescriptionActivity.this, TabActivity.class);
        startActivity(intent);
        finish();
    }
}
