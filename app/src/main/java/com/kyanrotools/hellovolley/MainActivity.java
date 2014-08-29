package com.kyanrotools.hellovolley;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

import model.BijinResultGson;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        TextView tv4 = (TextView) findViewById(R.id.textView);


        HandlerThread hs = new HandlerThread("bijin_getter");
        hs.start();
        Handler syncApiHandler = new Handler(hs.getLooper());


        final String url = "http://bjin.me/api/?type=rand&count=10&format=json";


        syncApiHandler.post(new Runnable() {

            @Override
            public void run() {

                try {
                    RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
                    RequestFuture<String> future  = RequestFuture.newFuture();
                    StringRequest request = new StringRequest(url, future, future);
                    queue.add(request);


                    String json = future.get();
                    Log.d("my", json);
                    Gson gson = new Gson();



                    Type type = new TypeToken<List<BijinResultGson.BijinGson>>() {}.getType();
                    List<BijinResultGson.BijinGson> bijins = gson.fromJson(json, type);



                    System.out.println("count:" + bijins.size());
                    for (BijinResultGson.BijinGson bijin : bijins) {
                        System.out.println("category:" + bijin.category);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("error");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    System.out.println("error");
                }


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
