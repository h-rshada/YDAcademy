package com.ydacademy.dell.Yashodeep2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jetradar.desertplaceholder.DesertPlaceholder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @InjectView(R.id.btn_next) Button btn_next;
    @InjectView(R.id.btn_submit) Button btn_submit;
    @InjectView(R.id.imageview) ImageView imageview;
    @InjectView(R.id.radioGroup) RadioGroup radioGroup;
    @InjectView(R.id.txt_timer) TextView Text_timer;
    @InjectView(R.id.txt_queNumber) TextView text_queNumber;
    CountDownTimer countDownTimer;
    String answerKey, ans, data, userans, exam, subject, chapter, es, class1, responseCheck;
    ProgressBar progressBar ;
    int count, c, i, flag = 0, state = 0;
    UrlRequest urlRequest;
    JSONObject json_data;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.inject(this);
        count=0;
        c = 0;
        userans="";
        data="";
        ans=null;
        countDownTimer = new Mycountdowntimer(60000, 1000);
        exam=getIntent().getStringExtra("Exam");
        subject=getIntent().getStringExtra("Subject");
        es=getIntent().getStringExtra("ES");
        chapter=getIntent().getStringExtra("Chapter");
        Log.d("ES***",es+chapter);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1=sp.getString("CLASS",null);
        Log.d("Class***",class1);
        actionBarSetup();
        progressBar=(ProgressBar)findViewById(R.id.progress);
        load_image();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick({R.id.btn_submit,R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:

                if (flag == 0 || count == 50)
                    check_result();
                while (userans.length() < 50) {
                    userans+="E";

                }

                // Log.d("Date",)
                urlRequest=UrlRequest.getObject();
                urlRequest.setContext(TestActivity.this);
                Log.d("URL***", "onClick: " + "http://yashodeepacademy.co.in/fetchanswerkeys.php?examcode=" + es + chapter + "&class=" + class1);
                urlRequest.setUrl("http://yashodeepacademy.co.in/fetchanswerkeys.php?examcode="+es+chapter+"&class="+class1);
                urlRequest.getResponse(new ServerCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("Response", response);
                        if (state == 0 && (!response.equals("Invalid subject code"))) {
                            try {

                                JSONArray jArray = new JSONArray(response);
                                answerKey = "";
                                for (i = 0; i < jArray.length(); i++) {
                                    json_data = jArray.getJSONObject(i);
                                    Log.d("wth", "onSuccess: " + json_data.getString("ans"));
                                    answerKey += json_data.getString("ans");
                                }
                                Log.d("answerkey***", answerKey.length() + "");
                                Log.d("userans***", userans.length() + "");
                                Log.d("userans***", userans + " ");
                                Log.d("state: ", state + "");
                                while (answerKey.length() < 50) {
                                    answerKey += "0";

                                }

                                if (count < 50) {
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(TestActivity.this);

                                    alertDialog.setMessage("Do you really want to submit test? ");

                                    alertDialog.setPositiveButton(
                                            "Yes",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    userans = userans.substring(0, 50);
                                                    Log.d("substring", userans);
                                                    data = userans + " " + answerKey;
                                                    callIntent();
                                                    dialog.cancel();

                                                }
                                            });

                                    alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });
                                    alertDialog.show();
                                } else {
                                    userans = userans.substring(0, 50);
                                    data = userans + " " + answerKey;
                                    Log.d("substring", userans);
                                    Log.d("substringl", userans.length() + "");
                                    callIntent();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        } else {
                            //Toast.makeText(TestActivity.this, "insert answer entry in DB", Toast.LENGTH_LONG).show();
                        }


                    }
                });

                break;
            case R.id.btn_next:
                flag=1;
                if (!(c == 1))
                    check_result();

                load_image();
                flag=0;
                if (count == 49) {

                    btn_next.setEnabled(false);
                }

                break;
        }
    }

    public void load_image()
    {
        Log.d("Url", "http://yashodeepacademy.co.in/" + class1 + "/" + es + chapter + "/q" + (count + 1) + ".PNG");
        Glide.with(getApplicationContext()).load("http://yashodeepacademy.co.in/" + class1 + "/" + es + chapter + "/q" + (count + 1) + ".PNG").asBitmap().override(600, 600)
                   .placeholder(null).listener(new RequestListener<String, Bitmap>() {
               @Override
               public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                   countDownTimer.cancel();

                   c++;
                   text_queNumber.setText(count + 1 + ".");
                   if (c == 2) {
                       count++;
                       c = 0;
                   }
                   if (count == 3) {
                       state = 1;
                       DesertPlaceholder desertPlaceholder = (DesertPlaceholder) findViewById(R.id.placeholder);
                       desertPlaceholder.setVisibility(View.VISIBLE);
                       desertPlaceholder.setOnButtonClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Intent intent = new Intent(TestActivity.this, TabActivity.class);
                               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                               TestActivity.this.finishAffinity();
                               startActivity(intent);

                           }
                       });

                   }
                   if (count == 50)
                       btn_submit.performClick();
                   else
                       btn_next.performClick();
                   return false;
               }

               @Override
               public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {

                   text_queNumber.setText(count + 1 + ".");
                   countDownTimer.start();
                   count++;
                   c = 0;
                   return false;
               }
        }).error(R.drawable.sorryimagenotavailable).into(imageview);

    }

    public void check_result()
    {
        if(radioGroup.getCheckedRadioButtonId()!=-1)
        {
            RadioButton radioButton=((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId()));
            userans+=radioButton.getText().toString();
        }
        else
            userans+="E";
        if (count < 50)
        radioGroup.clearCheck();


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(TestActivity.this,"You can't go back",Toast.LENGTH_LONG).show();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home/" + exam + "/" + subject);
        }
    }

    public void callIntent() {
        Intent intent = new Intent(TestActivity.this, ResultActivity.class);
        intent.putExtra("data", data);
        intent.putExtra("Class", class1);
        intent.putExtra("Subject", subject);
        intent.putExtra("Exam", exam);
        intent.putExtra("ES", es);
        intent.putExtra("Chapter", chapter);
        TestActivity.this.finish();
        startActivity(intent);
        finish();
    }

    public class Mycountdowntimer extends CountDownTimer {
        public Mycountdowntimer(long starttime, long interval)

        {
            super(starttime, interval);
        }


        @Override
        public void onTick(long miliseconds) {
            Text_timer.setText(miliseconds / 1000 + ":00");

        }

        @Override
        public void onFinish() {

            if (count == 50)
                btn_submit.performClick();
            else {
                check_result();
                load_image();
            }

        }

    }
}
