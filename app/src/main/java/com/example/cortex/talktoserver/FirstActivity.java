package com.example.cortex.talktoserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cortex.talktoserver.model.User;

public class FirstActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ivLogo =(ImageView)findViewById(R.id.iv_logo);
        tvWelcome =(TextView)findViewById(R.id.tvWelcome);


        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(FirstActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
        tvWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(FirstActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

    }
}
