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

import butterknife.InjectView;
import butterknife.OnClick;

public class OurAchievers extends AppCompatActivity {


    UrlRequest urlRequest;
    AdapterStudent adapter;
    List<DataStudent> data;
    DataStudent studentData;
    ProgressDialog loading;
    @InjectView(R.id.img_back)
    ImageView imageback;
    Toolbar toolbar;
    /*  @InjectView(R.id.Liststudent)*/ RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_achievers);
        //  ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(OurAchievers.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchstudentacheivers.php");
        loading = ProgressDialog.show(OurAchievers.this, "Loading", "Please wait.....", false, false);

        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                       final List<DataStudent> data = new ArrayList<>();
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               studentData = new DataStudent();
                                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               studentData.name = jsonObject.getString("name");
                                               studentData.id = jsonObject.getString("id");
                                               studentData.class1 = jsonObject.getString("class");
                                               studentData.description = jsonObject.getString("desc");
                                               data.add(studentData);
                                           }
                                           Log.d("Size", data.size() + "");
                                           recyclerView = (RecyclerView) findViewById(R.id.Liststudent);

                                           recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                               @Override
                                               public void onItemClick(View view, int position) {
                                                   // Toast.makeText(OurAchievers.this,"On"+data.get(position),Toast.LENGTH_LONG).show();
                                                   Intent intent = new Intent(OurAchievers.this, AchieversDescription.class);
                                                   intent.putExtra("Name", data.get(position).name);
                                                   //intent.putExtra("Id", data.get(position).id);
                                                   intent.putExtra("Class", data.get(position).class1);
                                                   intent.putExtra("Desc", data.get(position).description);
                                                   intent.putExtra("Url", "http://yashodeepacademy.co.in/studentacheivers/" + data.get(position).id + ".jpg");
                                                   startActivity(intent);
                                               }
                                                   })
                                           );
                                           recyclerView.setVisibility(View.VISIBLE);
                                           adapter = new AdapterStudent(OurAchievers.this, data);
                                           recyclerView.setAdapter(adapter);
                                           recyclerView.setLayoutManager(new GridLayoutManager(OurAchievers.this, 2));
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
                Intent intent = new Intent(OurAchievers.this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
