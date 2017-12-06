package com.example.user.ydacademy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.et_username)
    EditText edtUsername;
    @InjectView(R.id.et_password)
    EditText edtPassword;
    @InjectView(R.id.btnLogin)
    Button btnLogin;
    @InjectView(R.id.cv)
    CardView cardView;
    @InjectView(R.id.txtForgotPassword)
    TextView txtForgotPassword;
    @InjectView(R.id.relativeLogin)
    RelativeLayout relativeLayout;
    @InjectView(R.id.img_back)
    ImageView imageback;
    Toolbar toolbar ;
    UrlRequest urlRequest;
    String username, password, name, id, class1;

    SharedPreferences sp;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        try {
            File f = new File("/data/data/com.xoxytech.ostello/shared_prefs/YourSharedPreference.xml");
            if (f.exists()) {
                Log.d("TAG", "SharedPreferences Name_of_your_preference : exist");
                SharedPreferences sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                String username = sp.getString("USERNAME", null);
                if (username != null) {

                }
            } else
                Log.d("TAG", "Setup default preferences");

        } catch (Exception e) {
            e.printStackTrace();
        }
        ButterKnife.inject(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btnLogin, R.id.txtForgotPassword,R.id.img_back}) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnLogin:
                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.bounce);
                //  btnLogin.setAnimation(animation);

                username = edtUsername.getText().toString().trim();
                password = edtPassword.getText().toString().trim();
                sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                id = sp.getString("ID", null);
                name = sp.getString("USERNAME", null);
                class1 = sp.getString("CLASS", null);

                urlRequest = UrlRequest.getObject();
                urlRequest.setContext(LoginActivity.this);
                urlRequest.setUrl("http://192.168.0.22:8003/login_verification.php?username=" + username + "&password=" + password);
                urlRequest.getResponse(new ServerCallback() {
                                           @Override
                                           public void onSuccess(String response) {
                                               try {
                                                   Log.d("Response", response);

                                                   if (!response.contains("Invalid Username or password")) {
                                                       JSONArray jsonArray = new JSONArray(response);
                                                       for (int i = 0; i < jsonArray.length(); i++) {
                                                           JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                           id = jsonObject.getString("id");
                                                           class1 = jsonObject.getString("class");
                                                           name = jsonObject.getString("name");
                                                       }
                                                       SharedPreferences.Editor editor = sp.edit();
                                                       if (!(class1.equals("10"))) {
                                                           editor.putString("ID1", id);
                                                           editor.putString("CLASS1", class1);
                                                           editor.commit();
                                                       }
                                                       else {
                                                           editor.putString("ID1",null);
                                                           editor.commit();
                                                       }
                                                       editor.putString("ID", id);
                                                       editor.putString("CLASS", class1);
                                                       editor.putString("USERNAME", name);
                                                       editor.commit();
                                                       Explode explode = new Explode();
                                                       explode.setDuration(500);
                                                       getWindow().setExitTransition(explode);
                                                       getWindow().setEnterTransition(explode);
                                                       if (name.equalsIgnoreCase("Admin")) {
                                                           intent = new Intent(LoginActivity.this, StudentStatforAdmin.class);
                                                           startActivity(intent);
                                                           onBackPressed();

                                                       }
                                                       // ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
                                                       else {
                                                           intent = new Intent();
                                                           intent.putExtra("data", true);
                                                           setResult(RESULT_OK, intent);
                                                           Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                                                           finish();
                                                       }
                                                       return;
                                                   } else {
                                                       Snackbar.make(relativeLayout, "Invalid Login", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                                       Log.d("Invalidlogin", "onSuccess: ");
                                                   }
                                               } catch (JSONException e) {
                                                   e.printStackTrace();
                                               }
                                               Log.d("Id", id);
                                               Log.d("Username", name);
                                               Log.d("Class***", class1);
                                           }
                                       }
                );

                break;

            case R.id.txtForgotPassword:

                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(true);
                View detailView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_forgot_password, null, false);
                builder.setView(detailView);
                //ButterKnife.inject(this, detailView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case  R.id.img_back:
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent = new Intent();
        intent.putExtra("data", false);
        setResult(RESULT_OK, intent);
        finish();
    }
}
