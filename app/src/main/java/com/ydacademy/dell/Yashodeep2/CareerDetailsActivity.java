package com.ydacademy.dell.Yashodeep2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CareerDetailsActivity extends AppCompatActivity {
    //TextView txtName, txtDesc, txtScope, txtRelevantCourses;
    // @InjectView(R.id.recyclerCareer)RecyclerView recyclerView;
    @InjectView(R.id.img_back)
    ImageView imageback;
    @InjectView(R.id.txtName)
    TextView txtName;
    @InjectView(R.id.txtDesc)
    TextView txtDesc;
    @InjectView(R.id.txtScope)
    TextView txtScope;
    @InjectView(R.id.txtRelevantCourses)
    TextView txtCourse;
    Toolbar toolbar;
    AdapterCareer adapterCareer;
    ArrayList<DataCareer> arrayList;
    UrlRequest urlRequest;
    DataCareer dataCareer;
    String class1, title, scope, desc, course;

    /*   ArrayAdapter adapter_ten, adapter_twelve;
    String item;
    String title,desc,scope,releavant;
    ArrayList arrayList1;
    UrlRequest urlRequest;

    SharedPreferences sp;
   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_details);
        ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        //class1 = getIntent().getStringExtra("Class");
        Log.d("In details", "Career");
        // Log.d("Class", class1);
        title = getIntent().getStringExtra("Title");
        desc = getIntent().getStringExtra("Desc");
        scope = getIntent().getStringExtra("Scope");
        course = getIntent().getStringExtra("Course");
        txtName.setText(title);
        txtDesc.setText(desc);
        txtScope.setText(scope);
        txtCourse.setText(course);

       /*  dataCareer = new DataCareer();
        arrayList = new ArrayList<>();
        Log.d("Arraylist",arrayList.size()+"");
        adapterCareer = new AdapterCareer(CareerDetailsActivity.this, arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterCareer);
        adapterCareer.notifyDataSetChanged();*/
    }


    /* public void getData() {
          urlRequest = UrlRequest.getObject();
          urlRequest.setContext(CareerDetailsActivity.this);
          urlRequest.setUrl("http://yashodeepacademy.co.in/admin/routes/server/fetchCareer.php?class=" + class1);
          Log.d("URL", "http://yashodeepacademy.co.in/admin/routes/server/fetchCareer.php?class=" + class1);
          urlRequest.getResponse(new ServerCallback() {
                                     @Override
                                     public void onSuccess(String response) {

                                         Log.d("Response***", response);
                                         try {
                                             dataCareer = new DataCareer();
                                             arrayList = new ArrayList<>();
                                             JSONArray jsonArray = new JSONArray(response);
                                             for (int i = 0; i < jsonArray.length(); i++) {
                                                 JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                 dataCareer.title = jsonObject.getString("title");
                                                 dataCareer.desc = jsonObject.getString("description");
                                                 dataCareer.jobscope = jsonObject.getString("job_scope");
                                                 dataCareer.course = jsonObject.getString("relevant_course");
                                                 // dataCareer.career=jsonObject.getString("career");
                                                 arrayList.add(dataCareer);
                                             }

                                             Log.d("Arraylist1", arrayList.size()+ "");
                                             Log.d("career", dataCareer.title);
                                             Log.d("desc", dataCareer.desc);
                                             Log.d("scope", dataCareer.jobscope);
                                             Log.d("course", dataCareer.course);
                                           *//*  adapterCareer = new AdapterCareer(CareerDetailsActivity.this, arrayList);
                                           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                           linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                                           recyclerView.setLayoutManager(linearLayoutManager);
                                           recyclerView.setAdapter(adapterCareer);
                                           adapterCareer.notifyDataSetChanged();
*//*
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }


                                   }
                               }
        );


    }*/
    @OnClick({R.id.img_back}) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                Intent intent = new Intent(CareerDetailsActivity.this, CareerGuidance.class);
                startActivity(intent);
                break;
        }

    }
}
