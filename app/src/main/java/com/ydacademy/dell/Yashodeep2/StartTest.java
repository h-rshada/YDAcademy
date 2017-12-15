package com.ydacademy.dell.Yashodeep2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class StartTest extends AppCompatActivity {

    @InjectView(R.id.btn_startTest)Button start_test;
    String exam, subject, class1, es, id, chapter, standard, user;
    int count;
    @InjectView(R.id.img_back)
    ImageView imageback;
    Toolbar toolbar ;
    UrlRequest urlRequest;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        exam = getIntent().getStringExtra("Exam");
        subject = getIntent().getStringExtra("Subject");
        class1 = getIntent().getStringExtra("Class");
        chapter = getIntent().getStringExtra("position");
        es = getIntent().getStringExtra("ES");
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
        id = sp.getString("ID", null);

        Log.d("Class***", class1);
        Log.d("subject", subject);
        Log.d("ES*****", es);
        Log.d("chapter*", chapter);
        Log.d("ID", id);

    }

    @OnClick({R.id.btn_startTest,R.id.img_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startTest:
                Animation animation = AnimationUtils.loadAnimation(StartTest.this, R.anim.fadeout);
                start_test.startAnimation(animation);
                urlRequest = UrlRequest.getObject();
                urlRequest.setContext(StartTest.this);
                urlRequest.setUrl("http://yashodeepacademy.co.in/getexamcount.php?student_id=" + id + "&examcode=" + es + chapter + "&class=" + class1);
                urlRequest.getResponse(new ServerCallback() {
                                           @Override
                                           public void onSuccess(String response) {
                                               Log.d("ResponseCount", response);
                                               Log.d("***","http://yashodeepacademy.co.in/getexamcount.php?student_id=" + id + "&examcode=" + es + chapter + "&class=" + class1);
                                               try {
                                                   JSONArray jsonArray = new JSONArray(response);
                                                   for (int i = 0; i < jsonArray.length(); i++) {
                                                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                       count = jsonObject.getInt("count(id)");

                                                       Log.d("Count****", jsonObject.getInt("count(id)") + "");
                                                   }
                                                   if (user.equals("guest")) {
                                                       if (count < 1) {
                                                           Intent intent = new Intent(StartTest.this, GuestTest.class);
                                                           intent.putExtra("Class", class1);
                                                           intent.putExtra("Subject", subject);
                                                           intent.putExtra("Exam", exam);
                                                           intent.putExtra("ES", es);
                                                           intent.putExtra("Chapter", chapter);
                                                           startActivity(intent);
                                                           finish();
                                                       } else {
                                                           AlertDialog.Builder alertDialog = new AlertDialog.Builder(StartTest.this);

                                                           alertDialog.setMessage("To solve more test you have to subscribed ");

                                                           alertDialog.setPositiveButton(
                                                                   "Ok",
                                                                   new DialogInterface.OnClickListener() {
                                                                       public void onClick(DialogInterface dialog, int id) {
                                                                           dialog.cancel();
                                                                           Intent intent = new Intent(StartTest.this, TabActivity.class);
                                                                           startActivity(intent);
                                                                           finish();

                                                                       }
                                                                   });
                                                           alertDialog.show();
                                                       }
                                                   } else {
                                                       if (count < 2) {
                                                           Intent intent = new Intent(StartTest.this, TestActivity.class);
                                                           intent.putExtra("Class", class1);
                                                           intent.putExtra("Subject", subject);
                                                           intent.putExtra("Exam", exam);
                                                           intent.putExtra("ES", es);
                                                           intent.putExtra("Chapter", chapter);
                                                           startActivity(intent);
                                                           finish();
                                                       } else
                                                           Toast.makeText(StartTest.this, "Sorry,You can attemp test maximum 2 times", Toast.LENGTH_LONG).show();

                                                   }
                                               } catch (JSONException e1) {
                                                   e1.printStackTrace();
                                               }
                                           }
                                       }
                );
                break;
            case R.id.img_back:
                Intent intent=new Intent(StartTest.this,TabActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


}
