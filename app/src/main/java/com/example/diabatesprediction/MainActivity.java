package com.example.diabatesprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaParser;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    JsonObjectRequest jsonObjectRequest;
    EditText pregnancies;
    EditText glucose;
    EditText bp;
    EditText skinthickness;
    EditText insulin;
    EditText bmi;
    EditText dpf;
    EditText age;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sendData(View view)
    {
        HashMap<String,Float> data = new HashMap<String, Float>(); //to send data in key value pair
        String url="https://diabetes-prediction-fastapi.herokuapp.com/predict";//on which url you want to work with
        queue = Volley.newRequestQueue(this);
        pregnancies=findViewById(R.id.age);
        Float preg=Float.parseFloat(pregnancies.getText().toString());//for pregnancies

        glucose=findViewById(R.id.glucose);
        Float glu= Float.parseFloat(glucose.getText().toString());//for glucose

        bp=findViewById(R.id.bp);
        Float bloodpressure=Float.parseFloat(bp.getText().toString());//for bloodpressure

        skinthickness=findViewById(R.id.st);
        Float st=Float.parseFloat(skinthickness.getText().toString());//for skinthickness

        insulin=findViewById(R.id.insulin);
        Float insu=Float.parseFloat(insulin.getText().toString());//for insulin

        bmi=findViewById(R.id.bmi);
        Float bmi_=Float.parseFloat(bmi.getText().toString());//for dpf

        dpf=findViewById(R.id.dpf);
        Float dpf_=Float.parseFloat(dpf.getText().toString());//for dpf

        age=findViewById(R.id.age);
        Float age_=Float.parseFloat(age.getText().toString());//for bmi



    //Putting values to my hashmap in key value pairs
        data.put("pregnancies",preg);
        data.put("glucose",glu);
        data.put("bp",bloodpressure);
        data.put("skinthickness",st);
        data.put("insulin",insu);
        data.put("bmi",bmi_);
        data.put("dpf",dpf_);

        data.put("age",age_);


        jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String ans= null;
                        try {
                            ans = response.getString("ans"); //I'll take out the value whose key is ans
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(),ans,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(),"Opps something went wrong",Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }
}