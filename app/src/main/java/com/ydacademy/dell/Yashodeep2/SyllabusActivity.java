package com.ydacademy.dell.Yashodeep2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SyllabusActivity extends AppCompatActivity {
    /*@InjectView(R.id.listChapters)
    ListView listView;*/
    @InjectView(R.id.webView)
    WebView webView1;

    Toolbar toolbar ;
    UrlRequest urlRequest;
    SharedPreferences sp;
    String class1, class2;
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
        class1 = sp.getString("CLASS", null);
        if (class1.equals("guest")) {
            class2 = sp.getString("CLASS1", null);

            if (class2.equals("11")) {
                class1 = "11";
            } else {
                class1 = "12";
            }
        }
        webView1.loadUrl("https://docs.google.com/gview?embedded=true&url=http://yashodeepacademy.co.in/admin/routes/syllabus/" + class1 + exam + subject + ".pdf");
        loading.dismiss();
        Log.d("PDF", "https://docs.google.com/gview?embedded=true&url=http://yashodeepacademy.co.in/admin/routes/syllabus/" + class1 + exam + subject + ".pdf");
    }



}
