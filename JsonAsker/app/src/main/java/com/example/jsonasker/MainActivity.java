package com.example.jsonasker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.jtext);
        System.out.println(textView);
    }

    public void jsonText (View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ip.jsontest.com";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response.substring(8, 23));
                Pattern pattern = Pattern.compile(".*[0-9].+.*");

                textView.setText(response.substring(8, 22));
                System.out.println("Done");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (textView != null) {
                    System.out.println("Your textView: " + textView);
                } else {
                    System.out.println("It is NULL");
                }
                textView.setText("Didn't get");
            }
        });

        queue.add(stringRequest);
    }
}


