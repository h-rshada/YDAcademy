package com.ydacademy.dell.Yashodeep2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GuestRegistrationActivity extends AppCompatActivity {


    /* @InjectView(R.id.editTextUserId)EditText edtUserId;*/
    @InjectView(R.id.editTextPassword)
    EditText edtPass;
    @InjectView(R.id.editTextUsername)
    EditText edtName;
    @InjectView(R.id.editTextPhone)
    EditText edtPhone;
    @InjectView(R.id.editTextEmail)
    EditText edtEmail;
    @InjectView(R.id.buttonRegister)
    Button btnRegister;
    UrlRequest urlRequest;
    String name, password, emailid, phone;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_registration);
        ButterKnife.inject(this);
       /* Random r1 = new Random();
        StringBuilder builder=new StringBuilder();
        for(int count=1; count<=6;count++) {
            builder.append(r1.nextInt(10));
        }
        edtUserId.setText(builder.toString());*/

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edtName.getText().toString().trim();

                if (name.length() == 0) {
                    edtName.setError("Please enter name");
                    flag = 1;
                }
                password = edtPass.getText().toString().trim();
                emailid = edtEmail.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (!(emailid.matches(emailPattern)) || emailid.length() == 0) {
                    edtEmail.setError("Please enter emailid");
                    flag = 1;
                }
                phone = edtPhone.getText().toString().trim();
                if (phone.length() < 10 || phone.length() == 0) {
                    edtPhone.setError("Please enter phone number");
                    flag = 1;
                }
                if (flag == 0) {
                    urlRequest = UrlRequest.getObject();
                    urlRequest.setContext(getApplicationContext());
                    urlRequest.setUrl("http://yashodeepacademy.co.in/admin/routes/server/putUserData.php?name=" + name + "&password=" + password + "&emailid=" + emailid + "&phone=" + phone);
                    urlRequest.getResponse(new ServerCallback() {
                                               @Override
                                               public void onSuccess(String response) {
                                                   Log.d("Response", response);
                                                   if (response.contains("OK")) {

                                                       Toast.makeText(GuestRegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                                       Intent intent = new Intent(GuestRegistrationActivity.this, LoginActivity.class);
                                                       startActivity(intent);
                                                       GuestRegistrationActivity.this.finish();
                                                   } else if (response.contains("EXISTS")) {
                                                       Toast.makeText(GuestRegistrationActivity.this, "User already exist", Toast.LENGTH_LONG).show();
                                                   }


                                               }
                                           }
                    );
                }

            }
        });
        flag = 0;

    }
}
