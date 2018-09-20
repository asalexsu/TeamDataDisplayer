package com.example.alex.teamdatadisplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
// Import our Widget classes
import android.widget.TextView;
import android.webkit.WebView;
/**
        * This is a vex team data displayer program that allow users to input a team number to look up the stats of a team
        *
        * @author Alex Su
        * @version 2018.5.28
        */
public class Main2Activity extends AppCompatActivity {
    // Declare reference variables for our widgets
    protected static TextView teamRatingTextView;
    protected static TextView teamRankTextView;
    private  WebView webBrowser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //change the title name
        getSupportActionBar().setTitle("Detailed Stats");
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialize our widget reference variables
        teamRatingTextView = findViewById(R.id.teamRatingTextView);
        teamRankTextView=findViewById(R.id.teamRankTextView);
        webBrowser = findViewById(R.id.webBrowser);
        //fetch the data and display
        fetchRankData process = new fetchRankData();
        process.execute();
        // Enable JavaScript for our WebView
        webBrowser.getSettings().setJavaScriptEnabled(true);

        // Load a URL
        webBrowser.loadUrl("https://vexdb.io/teams/view/"+MainActivity.teamNumber);
    }
}
