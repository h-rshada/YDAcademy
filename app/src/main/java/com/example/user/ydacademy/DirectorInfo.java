package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
    UrlRequest urlRequest;
    JSONObject jsonObject;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director_info);
        ButterKnife.inject(this);
        actionBarSetup();
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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home/Director desk");
        }
    }
}
