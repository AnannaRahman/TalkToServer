package com.example.cortex.talktoserver;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cortex.talktoserver.helper.EndPoints;
import com.example.cortex.talktoserver.model.User;

import java.util.HashMap;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {
    private EditText etFirstName, etLastName, etAge;
    private FancyButton btnSave;
    RequestQueue queue;
    private ListView listView;
    User user;
    private EditText etId;
    private String value="new";
    private FancyButton btnSeeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(MainActivity.this);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etAge = (EditText) findViewById(R.id.etAge);
       // etId = (EditText) findViewById(R.id.etId);

        btnSave = (FancyButton) findViewById(R.id.btnSave);
        btnSeeList = (FancyButton) findViewById(R.id.btnSeeList);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                //user.setUserId(etId.getText().toString());
                user.setUserFirstName(etFirstName.getText().toString());
                user.setUserLastName(etLastName.getText().toString());
                user.setUserAge(etAge.getText().toString());
               sendDataToServer(user);
                    clearData();
                    Intent i=new Intent(MainActivity.this, ShowDetailsActivity.class);
                    startActivity(i);

            }
        });

        btnSeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, ShowDetailsActivity.class);
                startActivity(i);

            }
        });

    }

    private void clearData() {
        etFirstName.setText("");
        etLastName.setText("");
        etAge.setText("");
      //  etId.setText("");

    }

    private void sendDataToServer(final User userObject) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, EndPoints.POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

                        queue.getCache().clear();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                Log.d("response", error.toString());
            }
        }
        ) {


            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                try {
                    //params.put("id", userObject.getUserId());
                    params.put("first_name", userObject.getUserFirstName());
                    params.put("last_name", userObject.getUserLastName());
                    params.put("age", userObject.getUserAge());


                } catch (Error e) {
                    e.printStackTrace();
                }
                return params;
            }
        };
        queue.add(postRequest);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
