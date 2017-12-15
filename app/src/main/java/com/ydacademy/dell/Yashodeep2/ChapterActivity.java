package com.ydacademy.dell.Yashodeep2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ChapterActivity extends AppCompatActivity {

    @InjectView(R.id.listChapter)
    ListView listChapters;
    @InjectView(R.id.img_back)
    ImageView imageView;
    Toolbar toolbar ;
    UrlRequest urlRequest;
    String exam;
    Intent intent;
    String subject, class1, chapter, es, chaptercode, user, standard;
    SharedPreferences sp;
    List<String> chapters;
    ArrayAdapter adapter;
    ProgressDialog loading;
    int pos;
    char e, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        chapters = new ArrayList<>();
        exam = getIntent().getStringExtra("Exam");
        subject = getIntent().getStringExtra("Subject");
        Log.d("Exam", exam);
        Log.d("Sub", subject);

        e = exam.toLowerCase().charAt(0);
        Log.d("InitialExam", e + "");
        s = subject.toLowerCase().charAt(0);
        Log.d("ExamSubject", s + "");

        es = e + "" + s;
        Log.d("E***", exam);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1 = sp.getString("CLASS", null);
        standard = sp.getString("CLASS1", null);
        Log.d("Class***", class1);
        Log.d("Standard***", standard);
        user = class1;
        if (user.equals("guest")) {
            class1 = standard;
        }

        getData();
    }

    public void getData() {
        loading = ProgressDialog.show(ChapterActivity.this, "Loading", "Please wait.....", false, true);
        loading.setOnKeyListener(new ProgressDialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    finish();
                    loading.dismiss();
                }
                return true;
            }
        });
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(ChapterActivity.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchchaptername.php?subjectcode=" + es + "&class=" + class1);
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {

                                       Log.d("Response", response);
                                       try {
                                           JSONObject jsonObject = new JSONObject(response);
                                           for (int i = 1; i <= jsonObject.length(); i++) {
                                               // JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               Log.d("json", jsonObject.get(i + "") + "");
                                               //chapter = jsonObject.getString("name");
                                               // chaptercode = jsonObject.getString("chaptercode");
                                               chapters.add("" + i + ". " + jsonObject.get(i + "") + "");
                                           }

                                       } catch (JSONException e1) {
                                           e1.printStackTrace();
                                       }
                                       loading.dismiss();

                                       //Log.d("Chapters", chapters.toString() + "");
                                       adapter = new ArrayAdapter(ChapterActivity.this, android.R.layout.simple_list_item_1, chapters);
                                       listChapters.setAdapter(adapter);
                                       adapter.notifyDataSetChanged();

                                   }

                               }
        );
    }

    @OnItemClick(R.id.listChapter)
    public void onItemClick(int position) {
        chapters.get(position);
       /* pos=Integer.parseInt(chapters.get(position));*/
        Log.d("***", position + "");
        intent = new Intent(ChapterActivity.this, StartTest.class);
        intent.putExtra("Subject", subject);
        intent.putExtra("Class", class1);
        intent.putExtra("Exam", exam);
        intent.putExtra("ES", es);
        intent.putExtra("position", (position + 1) + "");
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.img_back) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                Intent intent2 = new Intent(ChapterActivity.this, TabActivity.class);
                startActivity(intent2);
                finish();
                break;
        }

    }

}
