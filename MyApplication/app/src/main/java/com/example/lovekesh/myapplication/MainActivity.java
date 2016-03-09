package com.example.lovekesh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button scenario1_btn;
    private Button scenario2_btn;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        scenario1_btn=(Button)findViewById(R.id.scenario1_btn);
        scenario2_btn=(Button)findViewById(R.id.scenario2_btn);
        scenario1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,Scenario1Activity.class);
                startActivity(intent);
            }
        });
        scenario2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext,Scenario2Activity.class);
                startActivity(intent);

            }
        });
    }

}
