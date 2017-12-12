package com.ydacademy.dell.Yashodeep2;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class CareerGuidenceFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner1, spinner2;
    View view, section1, section2;
    TextView header2, header1;
    boolean flag;
    Intent intent;
    String class1, title, desc, scope, releavant, career;
    DataCareer dataCareer;
    UrlRequest urlRequest;
    List<DataCareer> list;
    List<String> list1;
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_career_guidence, container, false);

        view.setBackgroundColor(Color.WHITE);
        intent = new Intent(getActivity(), CareerDetailsActivity.class);
        header1 = view.findViewById(R.id.header1);
        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        flag = false;

        spinner1.setOnItemSelectedListener(this);


        spinner2.setOnItemSelectedListener(this);

        section1 = view.findViewById(R.id.section1);
        section2 = view.findViewById(R.id.section2);


        header1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class1 = header1.getText().toString();
                getData(class1);
                intent.putExtra("Class", class1);
                if (section1.getVisibility() == View.GONE) {
                    section1.setVisibility(View.VISIBLE);
                    section2.setVisibility(View.GONE);
                    header2.setVisibility(View.GONE);
                    spinner1.setVisibility(View.VISIBLE);
                    spinner1.performClick();
                } else {
                    section1.setVisibility(View.GONE);
                    spinner1.setVisibility(View.GONE);
                    header2.setVisibility(View.VISIBLE);
                }
            }
        });
        header2 = view.findViewById(R.id.header2);
        header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class1 = header2.getText().toString();
                getData(class1);
                intent.putExtra("Class", class1);
                if (section2.getVisibility() == View.GONE) {
                    section2.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);
                    header1.setVisibility(View.GONE);
                    spinner2.setVisibility(View.VISIBLE);
                    spinner2.performClick();
                } else {
                    spinner2.setVisibility(View.GONE);
                    section2.setVisibility(View.GONE);
                    header1.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        {
            if (i > 0)
                intent.putExtra("Name", adapterView.getItemAtPosition(i).toString());
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void getData(final String class2) {
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(getActivity());
        urlRequest.setUrl("http://192.168.0.22:8002/routes/server/fetchCareer.php?class=" + class2);
        Log.d("URL", "http://192.168.0.22:8002/routes/server/fetchCareer.php?class=" + class2);
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       // list=new ArrayList<>();
                                       list1 = new ArrayList<>();
                                       dataCareer = new DataCareer();
                                       Log.d("Response", response);
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               // dataCareer.title = jsonObject.getString("title");
                                               // dataCareer.desc=jsonObject.getString("description");
                                               //  dataCareer.jobscope=jsonObject.getString("job_scope");
                                               //  dataCareer.course=jsonObject.getString("relevant_course");
                                               dataCareer.career = jsonObject.getString("career");
                                               list1.add(dataCareer.career);
                                               // list.add(dataCareer);
                                           }
                                           if (class2.equals("10")) {
                                               adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, list1);
                                               spinner1.setAdapter(adapter);
                                           } else {
                                               adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, list1);
                                               spinner2.setAdapter(adapter);
                                           }
                                           Log.d("------------------->", list1 + " ");


                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }

                                   }
                               }
        );


    }


}

