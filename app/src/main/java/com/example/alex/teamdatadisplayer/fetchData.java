package com.example.alex.teamdatadisplayer;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class fetchData extends AsyncTask<Void,Void,Void>{
    //variables to temp store the various data
    private String teamData="";
    private String teamNameString;
    private String programString;
    private String robotNameString;
    private String organizationString;
    private String cityString;
    private String regionString;
    private String countryString;
    private String gradeString;
    /**
     * This method will fetch data from the vexdb database and set the string variables to the data found on vexdb
     */
    @Override
    protected Void doInBackground(Void... voids) {
        try {

            URL url=new URL("https://api.vexdb.io/v1/get_teams?team="+MainActivity.teamNumber);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            //read every line
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

                teamNameString = JO.getString("team_name");
                programString = JO.getString("program");
                robotNameString = JO.getString("robot_name");
                organizationString = JO.getString("organisation");
                cityString=JO.getString("city");
                regionString=JO.getString("region");
                countryString=JO.getString("country");
                gradeString=JO.getString("grade");

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
        MainActivity.teamNameTextView.setText(teamNameString);
        MainActivity.programTextView.setText(programString);
        MainActivity.robotNameTextView.setText(robotNameString);
        MainActivity.organizationTextView.setText(organizationString);
        MainActivity.cityTextView.setText(cityString);
        MainActivity.regionTextView.setText(regionString);
        MainActivity.countryTextView.setText(countryString);
        MainActivity.gradeTextView.setText(gradeString);


    }
}
