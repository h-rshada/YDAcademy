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
    View view;// section1, section2;
    TextView header2, header1;
    boolean flag;
    Intent intent;
    String class1, title, desc, scope, releavant, career;
    DataCareer dataCareer;
    UrlRequest urlRequest;
    List<String> listTitle;
    List<String> listDesc;
    List<String> listScope;
    List<String> listCareer;
    List<String> listCourse;
    ArrayAdapter adapter;
    int iCurrentSelection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_career_guidence, container, false);
        view.setBackgroundColor(Color.WHITE);

        listTitle = new ArrayList<>();
        listDesc = new ArrayList<>();
        listScope = new ArrayList<>();
        listCareer = new ArrayList<>();
        listCourse = new ArrayList<>();

        dataCareer = new DataCareer();

        header1 = view.findViewById(R.id.header1);
        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        flag = false;

        spinner1.setOnItemSelectedListener(this);
        // iCurrentSelection = spinner1.getSelectedItemPosition();
        spinner2.setOnItemSelectedListener(this);

        //iCurrentSelection = spinner1.getSelectedItemPosition();

       /* section1 = view.findViewById(R.id.section1);
        section2 = view.findViewById(R.id.section2);*/

        header1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class1 = header1.getText().toString();
                getData(class1);
                spinner1.performClick();
                //intent.putExtra("Class", class1);

              /*  if (spinner1.getVisibility() == View.GONE) {
                   *//* section1.setVisibility(View.VISIBLE);
                    section2.setVisibility(View.GONE);*//*
                   // header2.setVisibility(View.GONE);
                    spinner1.setVisibility(View.VISIBLE);
                    spinner1.performClick();
                   // listCareer.clear();
                } else {
                    //section1.setVisibility(View.GONE);
                    spinner1.setVisibility(View.VISIBLE);
                    header2.setVisibility(View.VISIBLE);
                }*/
            }
        });
        header2 = view.findViewById(R.id.header2);
        header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class1 = header2.getText().toString();
                getData(class1);
                spinner2.performClick();
              /* // intent.putExtra("Class", class1);
                if (spinner2.getVisibility() == View.GONE) {
                    *//*section2.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);*//*
                    //header1.setVisibility(View.GONE);
                    spinner2.setVisibility(View.VISIBLE);
                   spinner2.performClick();
                   // listCareer.clear();
                } else {
                    spinner2.setVisibility(View.VISIBLE);
                   // section2.setVisibility(View.GONE);
                    header1.setVisibility(View.VISIBLE);
                }*/
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        {
            if (i > 0) {
                intent = new Intent(getActivity(), CareerDetailsActivity.class);
                // intent.putExtra("Name", adapterView.getItemAtPosition(i).toString());
                Log.d("TTTT", listTitle.get(i));
                intent.putExtra("Title", listTitle.get(i));
                intent.putExtra("Desc", listDesc.get(i));
                intent.putExtra("Scope", listScope.get(i));
                intent.putExtra("Course", listCourse.get(i));
                startActivity(intent);
            }

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void getData(final String class2) {
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(getActivity());
        urlRequest.setUrl("http://yashodeepacademy.co.in/admin/routes/server/fetchCareer.php?class=" + class2);
        Log.d("URL", "http://yashodeepacademy.co.in/admin/routes/server/fetchCareer.php?class=" + class2);
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {

                                       Log.d("Response", response);
                                       try {
                                           listTitle = new ArrayList<>();
                                           listDesc = new ArrayList<>();
                                           listScope = new ArrayList<>();
                                           listCareer = new ArrayList<>();
                                           listCourse = new ArrayList<>();
                                           JSONArray jsonArray = new JSONArray(response);
                                           Log.d("Size", listCareer.size() + "");
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               title = jsonObject.getString("title");
                                               desc = jsonObject.getString("description");
                                               scope = jsonObject.getString("job_scope");
                                               releavant = jsonObject.getString("relevant_course");
                                               career = jsonObject.getString("career");
                                               listTitle.add(title);
                                               listDesc.add(desc);
                                               listScope.add(scope);
                                               listCourse.add(releavant);
                                               listCareer.add(career);
                                           }
                                           adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, listCareer);
                                           // Log.d("Title",dataCareer.title);
                                           if (class2.equals("10")) {
                                               spinner1.setAdapter(adapter);

                                           } else {
                                               //adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, listCareer);
                                               spinner2.setAdapter(adapter);

                                           }
                                           // listCareer.clear();


                                           Log.d("------------------->", listCareer.size() + " ");
//                                           Log.d("------------------->", list.iterator());
//                                           Iterator i = list.iterator();

                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }

                                   }
                               }
        );


    }


}

