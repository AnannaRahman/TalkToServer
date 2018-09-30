package com.example.cortex.talktoserver;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cortex.talktoserver.adapter.UserAdapter;
import com.example.cortex.talktoserver.helper.EndPoints;
import com.example.cortex.talktoserver.model.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ShowDetailsActivity extends AppCompatActivity {
    RequestQueue queue;
    private ListView listView;
    User user;
    private ArrayList<User> arrayList;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<User>();
        queue = Volley.newRequestQueue(ShowDetailsActivity.this);
        adapter = new UserAdapter(ShowDetailsActivity.this, arrayList);
        listView.setAdapter(adapter);

        getDataFormServer();

    }

    private void getDataFormServer() {

        if (isNetworkAvailable()) {

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, EndPoints.GETDATA,null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    Log.e("send", response.toString());
                    int length = response.length();
                    for (int i = 0; i < length; i++) {
                        try {
                            User userObject = new User();
                            userObject.setUserFirstName(response.getJSONObject(i).optString("first_name"));
                            userObject.setUserLastName(response.getJSONObject(i).optString("last_name"));
                            userObject.setUserAge(response.getJSONObject(i).optString("age"));
                            // arrayList.add(userObject.getUserName().toString() + "\n" + userObject.getPhone().toString()+ "\n" + userObject.getEmail());
                            arrayList.add(userObject);

                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error", error.getCause().toString());
                }
            });
            queue.add(jsonArrayRequest);

        } else {
            Toast.makeText(ShowDetailsActivity.this, "Connect Your Internet Please", Toast.LENGTH_LONG).show();
        }
    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
