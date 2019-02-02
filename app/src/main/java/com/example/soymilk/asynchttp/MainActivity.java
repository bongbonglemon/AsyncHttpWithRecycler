package com.example.soymilk.asynchttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> listOfEarthquakes = new ArrayList<String>();

    Adapter chicken = new Adapter(listOfEarthquakes);
    RecyclerView view;
    AsyncHttpClient client = new AsyncHttpClient();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (RecyclerView)findViewById(R.id.rvChickens);
        view.setLayoutManager(new LinearLayoutManager(this)); // forgot this and nothing appeared
        view.setAdapter(chicken);
        getEarthquakeData();
    }

    private void getEarthquakeData() {
        client.get("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Toast.makeText(getApplicationContext(), "Status code is: " + statusCode, Toast.LENGTH_LONG).show();
                    parseJson(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void parseJson(JSONObject response) throws JSONException {
        JSONArray earthquakes = response.getJSONArray("features");
        for (int i = 0; i < earthquakes.length(); i++){
            JSONObject oneEarthquake = earthquakes.getJSONObject(i);
            JSONObject properties = oneEarthquake.getJSONObject("properties");
            String placeOfEarthquake = properties.getString("place");
            listOfEarthquakes.add(placeOfEarthquake);
            chicken.notifyItemChanged(listOfEarthquakes.size()-1);
        }

    }
}
