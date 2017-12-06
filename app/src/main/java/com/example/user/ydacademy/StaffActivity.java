package com.example.user.ydacademy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class StaffActivity extends AppCompatActivity {

    UrlRequest urlRequest;
    AdapterStaff adapter;
    @InjectView(R.id.Liststaff)
    RecyclerView listStaff;
    List<DataStaff> data;
    RecyclerView recyclerView;
    ProgressDialog loading;
    @InjectView(R.id.img_back)
    ImageView imageback;
    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        ButterKnife.inject(this);

        loading = ProgressDialog.show(StaffActivity.this, "Loading", "Please wait.....", false, false);

        final List<DataStaff> data = new ArrayList<>();
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(StaffActivity.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchteachersimages.php");
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               DataStaff staffData = new DataStaff();
                                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               staffData.name = jsonObject.getString("name");
                                               staffData.id = jsonObject.getString("id");
                                               staffData.degree = jsonObject.getString("degree");
                                               staffData.exp = jsonObject.getString("experience");
                                               staffData.description = jsonObject.getString("desription");
                                               data.add(staffData);
                                           }
                                           recyclerView = (RecyclerView) findViewById(R.id.Liststaff);
                                           recyclerView.setVisibility(View.VISIBLE);
                                           adapter = new AdapterStaff(StaffActivity.this, data);
                                           recyclerView.setAdapter(adapter);
                                           recyclerView.setLayoutManager(new GridLayoutManager(StaffActivity.this, 2));
                                           adapter.notifyDataSetChanged();
                                           loading.dismiss();

                                       } catch (JSONException e1) {
                                           e1.printStackTrace();
                                       }
                                   }
                               }
        );

    }

    @OnClick({R.id.img_back}) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                Intent intent = new Intent(StaffActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
