package com.woojin0417.listview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<JSONObject> arrayList;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList=new ArrayList<>();
        lv=(ListView)findViewById(R.id.listView);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://ec2-52-26-144-160.us-west-2.compute.amazonaws.com:3000/jiminzzang");

            }
        });
    }

    class ReadJSON extends AsyncTask<String,Integer,String>{


        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                org.json.JSONObject jsonObject = new org.json.JSONObject(content);
                org.json.JSONObject json =(org.json.JSONObject)jsonObject.get("testCase");
                JSONArray jsonArray=(JSONArray)json.get("testList");

                for(int i=0;i<jsonArray.length(); i++)
                {
                    org.json.JSONObject productObject=jsonArray.getJSONObject(i);
                    arrayList.add(new JSONObject(
                            productObject.getString("url"),
                            productObject.getString("rank"),
                            productObject.getString("Nm")

                    ));
                    Log.v("Helpme", productObject.getString("Nm"));

                }

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            ListAdapter adapter=new ListAdapter(
                    getApplicationContext(),R.layout.custom_list_layout,arrayList
            );
            lv.setAdapter(adapter);
        }
    }

    private static String readURL(String theUrl) {

        StringBuilder content = new StringBuilder();
        try {
            URL url= new URL(theUrl);

            URLConnection urlConnection=url.openConnection();

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while((line=bufferedReader.readLine())!=null)
            {
                content.append(line+"\n");
            }
            bufferedReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        return content.toString();
    }
}
