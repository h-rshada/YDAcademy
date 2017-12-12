package com.ydacademy.dell.Yashodeep2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CareerDetailsActivity extends AppCompatActivity {
    //TextView txtName, txtDesc, txtScope, txtRelevantCourses;
    @InjectView(R.id.img_back)
    ImageView imageback;
    @InjectView(R.id.recyclerCareer)
    RecyclerView recyclerView;
    Toolbar toolbar;
    AdapterCareer adapterCareer;
    ArrayList<DataCareer> arrayList;
    UrlRequest urlRequest;
    DataCareer dataCareer;
    String class1;

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
        dataCareer = new DataCareer();
        // class1=getIntent().getStringExtra("Class");
        // Log.d("Class",class1);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        class1 = getIntent().getStringExtra("Class");

        Log.d("In details", "Career");
        Log.d("Class", class1);
        getData();


    }


    public void getData() {
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(CareerDetailsActivity.this);
        urlRequest.setUrl("http://192.168.0.22:8002/routes/server/fetchCareer.php?class=" + class1);
        Log.d("URL", "http://192.168.0.22:8002/routes/server/fetchCareer.php?class=" + class1);
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {

                                       arrayList = new ArrayList<>();
                                       Log.d("Response***", response);
                                       try {
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
                                           Log.d("Arraylist1", arrayList + "");
                                           Log.d("career", dataCareer.title);
                                           Log.d("desc", dataCareer.desc);
                                           Log.d("scope", dataCareer.jobscope);
                                           Log.d("course", dataCareer.course);
                                           adapterCareer = new AdapterCareer(CareerDetailsActivity.this, arrayList);
                                           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                           linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                           recyclerView.setLayoutManager(linearLayoutManager);
                                           recyclerView.setAdapter(adapterCareer);
                                           adapterCareer.notifyDataSetChanged();

                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }


                                   }
                               }
        );


    }
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
