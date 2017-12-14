package com.ydacademy.dell.Yashodeep2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class ImpQuestions extends AppCompatActivity {

    UrlRequest urlRequest;
    List<String> chapters;
    ArrayAdapter adapter;
    String subject, class1;
    SharedPreferences sp;
    JSONObject jsonObject;
    char s;
    @InjectView(R.id.listImpQuestions)
    ListView listImpQuestions;
    /* @InjectView(R.id.img_back)
     ImageView imageback;*/
    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_questions);
        ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1 = sp.getString("CLASS", null);
        if (class1.equals("guest")) {
            class1 = "10";
        }
        Log.d("Class***", class1);
        subject = getIntent().getStringExtra("Subject");
        Log.d("Subject***", subject);
        s = subject.toLowerCase().charAt(0);
        Log.d("S", s + "");
        chapters = new ArrayList<>();
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(ImpQuestions.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/getimpquepdfnames.php?subject=" + s + "&class=" + class1);
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               jsonObject = jsonArray.getJSONObject(i);
                                               subject = jsonObject.getString("name");
                                               chapters.add(subject);
                                           }
                                       } catch (JSONException e1) {
                                           e1.printStackTrace();
                                       }
                                       Log.d("Chapters", chapters.toString() + "");
                                       adapter = new ArrayAdapter(ImpQuestions.this, android.R.layout.simple_list_item_1, chapters);
                                       listImpQuestions.setAdapter(adapter);
                                       adapter.notifyDataSetChanged();
                                   }
                               }
        );
    }
    @OnItemClick(R.id.listImpQuestions)
    public void onItemClick(int position) {
        Log.d("***", position + "");
        String pdfname = (String) listImpQuestions.getItemAtPosition(position);
        Intent intent = new Intent(ImpQuestions.this, ListQuestions.class);
        intent.putExtra("pdfname", pdfname);
        intent.putExtra("Sub", s + "");
        startActivity(intent);
    }
  /*  @OnClick({R.id.img_back}) *//* , R.id.fab*//*
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.img_back:
                Intent intent=new Intent(ImpQuestions.this,TabActivity.class);
                startActivity(intent);
                break;
        }

    }*/
}
