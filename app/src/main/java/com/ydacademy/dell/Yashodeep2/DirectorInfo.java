package com.ydacademy.dell.Yashodeep2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DirectorInfo extends AppCompatActivity {

    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.address)
    TextView address;
    @InjectView(R.id.phoneNumber)
    TextView phoneNumber;
    @InjectView(R.id.mobileNumber)
    TextView mobileNumber;
    @InjectView(R.id.emailid)
    TextView email;
    @InjectView(R.id.directorMsg)
    TextView directorMsg;
    @InjectView(R.id.img_back)
    ImageView imageback;

    Toolbar toolbar;
    UrlRequest urlRequest;
    JSONObject jsonObject;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director_info);
        ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(DirectorInfo.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchinfo.php");
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Responseinfo", response);
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               jsonObject = jsonArray.getJSONObject(i);
                                               message = jsonObject.getString("directors_desk");
                                               directorMsg.setText(message);
                                               directorMsg.setLetterSpacing(0.01f);
                                               Log.d("Responseinfo", message);
                                           }
                                       } catch (JSONException e1) {
                                           e1.printStackTrace();
                                       }

                                       Log.d("Chapters", message);

                                   }
                               }
        );
    }

    @OnClick({R.id.img_back}) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                Intent intent = new Intent(DirectorInfo.this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
