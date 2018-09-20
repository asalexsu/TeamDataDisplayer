package com.example.alex.teamdatadisplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
// Import our Widget classes
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;
/**
 * This is a vex team data displayer program that allow users to input a team number to look up the stats of a team
 *
 * @author Alex Su
 * @version 2018.5.28
 */
public class FeedBackActivity extends AppCompatActivity {
    // Declare reference variables for our widgets
    private RatingBar ratingBar;
    private TextView ratingDisplayTextView;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        //change the title name
        getSupportActionBar().setTitle("Feedback");
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize our widget reference variables
        ratingBar=findViewById(R.id.ratingBar);
        ratingDisplayTextView=findViewById(R.id.ratingTextView);
        submitButton=findViewById(R.id.submitButton);

        // Set the event listeners for our interactive widgets
        OnClickListener buttonEventListener = new ButtonListener();
        submitButton.setOnClickListener( buttonEventListener );
    }
    /**
     * This method will detect if any button clicks happens and respond accordingly
     */
    class ButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            //when the button gets clicked, display the rating user chose
            if (v.getId() == R.id.submitButton) {
                ratingDisplayTextView.setText("Your rating is:"+ ratingBar.getRating());

            }


        }
    }
}
