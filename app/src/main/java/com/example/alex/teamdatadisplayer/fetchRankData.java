package com.example.alex.teamdatadisplayer;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
        * This is a vex team data displayer program that allow users to input a team number to look up the stats of a team
        *
        * @author Alex Su
        * @version 2018.5.28
        */
public class fetchRankData extends AsyncTask<Void,Void,Void>{
    String teamData="";
    String ratingString;
    String ratingRankString;
    /**
     * This method will fetch data from the vexdb database and set the string variables to the data found on vexdb
     */
    @Override
    protected Void doInBackground(Void... voids) {
        try {

            URL url=new URL("https://api.vexdb.io/v1/get_season_rankings?team="+MainActivity.teamNumber+"&season="+URLEncoder.encode(String.valueOf(MainActivity.dropdown.getSelectedItem()),"UTF-8"));

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line !=null){
                line=bufferedReader.readLine();
                teamData=teamData+line;
            }
            JSONObject json=new JSONObject(teamData);
            //get result section of the JSON
            JSONArray JA= json.getJSONArray("result");
            //get data for each value and set the string to it
            for (int i=0;i<JA.length();i++){
                JSONObject JO= JA.getJSONObject(i);

                ratingString = JO.getString("vrating");
                ratingRankString = JO.getString("vrating_rank");


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * This method happens after the fetch, it will set all the textview to the desired data
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Display the results
        Main2Activity.teamRatingTextView.setText(ratingString);
        Main2Activity.teamRankTextView.setText(ratingRankString);



    }
}
