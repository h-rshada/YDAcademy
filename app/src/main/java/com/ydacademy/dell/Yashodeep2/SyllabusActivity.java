package com.ydacademy.dell.Yashodeep2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SyllabusActivity extends AppCompatActivity {
    /*@InjectView(R.id.listChapters)
    ListView listView;*/
    @InjectView(R.id.webView)
    WebView webView1;
    @InjectView(R.id.img_back)
    ImageView imageback;
    Toolbar toolbar ;
    UrlRequest urlRequest;
    SharedPreferences sp;
    private ProgressDialog loading;
    //String urlPDF="https://docs.google.com/gview?embedded=true&url=http://www.stafforini.com/txt/Covey%20-%20The%207%20habits%20of%20highly%20effective%20people.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        loading = new ProgressDialog(SyllabusActivity.this);

        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(SyllabusActivity.this);
        urlRequest.setUrl("http://ostallo.com/ostello/fetchcities.php");
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                   }
                               }
        );
        Log.d("loading****","******");
        //loading = ProgressDialog.show(SyllabusActivity.this, "Loading", "Please wait.....", false, false);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                loading.setMessage("Loading");
                loading.show();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                loading.dismiss();

                String webUrl = webView1.getUrl();

            }


        });

        String exam = getIntent().getStringExtra("Exam");
        String subject = getIntent().getStringExtra("Subject");
        exam = exam.toLowerCase();
        subject = subject.toLowerCase();
        Log.d("Exam", exam);
        Log.d("subject", subject);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        String class1 = sp.getString("CLASS", null);
        webView1.loadUrl("https://docs.google.com/gview?embedded=true&url=http://yashodeepacademy.co.in/admin/routes/syllabus/" + class1 + exam + subject + ".pdf");
        loading.dismiss();
        Log.d("PDF", "https://docs.google.com/gview?embedded=true&url=http://yashodeepacademy.co.in/admin/routes/syllabus/" + class1 + exam + subject + ".pdf");
    }

    @OnClick({R.id.img_back}) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.img_back:
                Intent intent=new Intent(SyllabusActivity.this,TabActivity.class);
                startActivity(intent);
                break;
        }

    }

}
