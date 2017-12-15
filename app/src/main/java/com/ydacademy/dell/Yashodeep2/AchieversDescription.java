package com.ydacademy.dell.Yashodeep2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AchieversDescription extends AppCompatActivity {

    /*@InjectView(R.id.profile_image)
    ImageView imageProfile;*/
    /* @InjectView(R.id.txtId)
     TextView txtId;*/
    @InjectView(R.id.txtName)
    TextView txtName;
    @InjectView(R.id.txtClass)
    TextView txtClass;
    @InjectView(R.id.txtDesc1)
    TextView txtDescription;
    @InjectView(R.id.txtDesc2)
    TextView txtDescription1;
    TextView textView;
    @InjectView(R.id.img_back)
    ImageView imageback;
    /*@InjectView(R.id.btn_director)
       AppCompatButton btn_director;*/
    Toolbar toolbar;
    /*  @InjectView(R.id.progress)
      ProgressBar progressBar;*/
    String id, name, desc, url, class1;
    String[] a;
    String desc1 = "1", desc2 = "2";
    //  DataStudent arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievers_description);
        ButterKnife.inject(this);
        // id = getIntent().getStringExtra("Id");
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        name = getIntent().getStringExtra("Name");
        class1 = getIntent().getStringExtra("Class");
        desc = getIntent().getStringExtra("Desc");
        a = desc.split("\\$");
        desc1 = a[0];
        desc2 = a[1];
        url = getIntent().getStringExtra("Url");
        txtName.setText(name);
        // txtId.setText(id);
        txtClass.setText(class1);
        txtDescription.setText(desc1);
        txtDescription1.setText(desc2);
        /*Glide.with(AchieversDescription.this).load(url).asBitmap().override(600, 600)
                .placeholder(null).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {

                progressBar.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).error(null)
                .into(imageProfile);
    }
*/
    }

    @OnClick({R.id.img_back}) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                Intent intent = new Intent(AchieversDescription.this, OurAchievers.class);
                startActivity(intent);
                break;

        }

    }
}



