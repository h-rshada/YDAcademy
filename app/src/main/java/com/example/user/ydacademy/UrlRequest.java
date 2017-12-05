package com.example.user.ydacademy;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by android on 10/24/17.
 */

public class UrlRequest {
    static UrlRequest myObj;
    String result;
    RequestQueue requestQueue;
    Context context;
    String url = "http://ostallo.com/ostello/fetchcities.php";

    /*   public UrlRequest(Context context)
       {
           this.context = context;

       }
   */
    private UrlRequest() {
    }

    public static UrlRequest getObject() {

        if (myObj == null)
            myObj = new UrlRequest();
        return myObj;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public String getResponse(final ServerCallback callback) {
        if (CheckInternet.checkinternet(context)) {
            requestQueue = Volley.newRequestQueue(context);
            result = "omg";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("*******", response.toString());
                            try {
                                callback.onSuccess(response);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if (error == null || error.networkResponse == null)
                                return;
                            final String statusCode = String.valueOf(error.networkResponse.statusCode);
                            //get response body and parse with appropriate encoding
                            if (error.networkResponse != null) {
                                Log.e("Volley", "Error. HTTP Status Code:" + error.networkResponse.statusCode);
                            }
                            if (error instanceof TimeoutError) {
                                Log.e("Volley", "TimeoutError");
                            } else if (error instanceof NoConnectionError) {
                                Log.e("Volley", "NoConnectionError");
                            } else if (error instanceof AuthFailureError) {
                                Log.e("Volley", "AuthFailureError");
                            } else if (error instanceof ServerError) {
                                Log.e("Volley", "ServerError");
                            } else if (error instanceof NetworkError) {
                                Log.e("Volley", "NetworkError");
                            } else if (error instanceof ParseError) {
                                Log.e("Volley", "ParseError");
                            }
                        }
                    });
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
        return result;
    }
}
