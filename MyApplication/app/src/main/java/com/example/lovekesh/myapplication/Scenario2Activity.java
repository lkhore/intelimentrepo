package com.example.lovekesh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.lovekesh.myapplication.model.Vehical;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Scenario2Activity extends AppCompatActivity {

    private Spinner name_spinner;
    private Context mContext;
    private String TAG = Scenario2Activity.class.getSimpleName();
    private ArrayList<Vehical> vehicalArrayList;
    private ArrayAdapter<Vehical> dataAdapter;
    private TextView car_distance_txtview;
    private TextView train_distance_txtview;
    private Button navigate_btn;
    private int select_postion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario2);
        mContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vehicalArrayList = new ArrayList<Vehical>();
        name_spinner = (Spinner) findViewById(R.id.name_spinner);
        car_distance_txtview = (TextView) findViewById(R.id.car_distance_txtview);
        train_distance_txtview = (TextView) findViewById(R.id.train_distance_txtview);
        navigate_btn = (Button) findViewById(R.id.navigate_btn);
        name_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car_distance_txtview.setText("Car - " + vehicalArrayList.get(position).getCar().toString());
                train_distance_txtview.setText("Train - " + vehicalArrayList.get(position).getTrain().toString());
                select_postion = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        navigate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    String geoCode = "geo:0,0?q=" + vehicalArrayList.get(select_postion).getLatitude() + ","
                            + vehicalArrayList.get(select_postion).getLongitude();
                    Intent sendLocationToMap = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(geoCode));
                    startActivity(sendLocationToMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
//        name_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                car_distance_txtview.setText("Car - " + vehicalArrayList.get(position).getCar().toString());
//                train_distance_txtview.setText("Train - " + vehicalArrayList.get(position).getTrain().toString());
//            }
//        });

        AQuery aQuery = new AQuery(mContext);
        aQuery.ajax("http://express-it.optusnet.com.au/sample.json", String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String object, AjaxStatus status) {
                super.callback(url, object, status);
                try {
                    JSONArray jsonArray = new JSONArray(object);
                    vehicalArrayList = new ArrayList<Vehical>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Vehical vehical = new Vehical();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (jsonObject.has("id")) {
                            vehical.setId(jsonObject.getInt("id"));
                        }
                        if (jsonObject.has("name")) {
                            vehical.setName(jsonObject.getString("name"));
                        }
                        if (jsonObject.has("fromcentral")) {
                            if (jsonObject.getJSONObject("fromcentral").has("car")) {
                                vehical.setCar(jsonObject.getJSONObject("fromcentral").getString("car"));
                            }
                            if (jsonObject.getJSONObject("fromcentral").has("train")) {
                                vehical.setTrain(jsonObject.getJSONObject("fromcentral").getString("train"));
                            }
                        }
                        if (jsonObject.has("location")) {
                            if (jsonObject.getJSONObject("location").has("latitude")) {
                                vehical.setLatitude(jsonObject.getJSONObject("location").getDouble("latitude"));
                            }
                            if (jsonObject.getJSONObject("location").has("longitude")) {
                                vehical.setLongitude(jsonObject.getJSONObject("location").getDouble("longitude"));
                            }
                        }

                        vehicalArrayList.add(vehical);

                    }

                    Log.e(TAG, "callback() called with: " + "url = [" + url + "], object = [" + vehicalArrayList + "], status = [" + status + "]");
                    dataAdapter = new ArrayAdapter<Vehical>(mContext, android.R.layout.simple_spinner_item, vehicalArrayList);
                    dataAdapter.setDropDownViewResource
                            (android.R.layout.simple_spinner_dropdown_item);
                    name_spinner.setAdapter(dataAdapter);
                    dataAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
