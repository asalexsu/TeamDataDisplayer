package com.example.alex.teamdatadisplayer;


import android.content.DialogInterface;
import android.content.Intent;
// Import SharedPreferences and its Editor class
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
// Import our Widget classes
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
/**
        * This is a vex team data displayer program that allow users to input a team number to look up the stats of a team
        *
        * @author Alex Su
        * @version 2018.5.28
        */
// Import Event handling classes for Button and EditText widgets
import android.view.View.OnClickListener;

// Import our Widget classes
import android.widget.Toast;

// Import Event handling classes for CheckBox widget
public class MainActivity extends AppCompatActivity {
    // Declare reference variables for our widgets
    private EditText teamNumberEditText;
    protected static TextView teamNameTextView;
    protected static TextView programTextView;
    protected static TextView robotNameTextView;
    protected static TextView organizationTextView;
    protected static TextView cityTextView;
    protected static TextView regionTextView;
    protected static TextView countryTextView;
    protected static TextView gradeTextView;
    protected static Spinner dropdown;
    private Button checkButton;
    private Button statButton;
    private Button feedbackButton;
    // Our event handlers will be sharing these
    protected static String teamNumber;
    private String[] items= new String[]{"Turning Point","In The Zone","Starstruck","Nothing But Net","Skyrise"};

    // Declare reference for a SharedPreferences object
    private SharedPreferences savedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize our widget reference variables
        teamNumberEditText = findViewById(R.id.teamNumberEditText);
        teamNameTextView = findViewById(R.id.teamNameTextView);
        programTextView = findViewById(R.id.programTextView);
        robotNameTextView = findViewById(R.id.robotNameTextView);
        organizationTextView = findViewById(R.id.organizationTextView);
        cityTextView = findViewById(R.id.cityTextView);
        regionTextView = findViewById(R.id.regionTextView);
        countryTextView = findViewById(R.id.countryTextView);
        gradeTextView = findViewById(R.id.gradeTextView);
        checkButton = findViewById(R.id.checkButton);
        dropdown=  findViewById(R.id.spinner);
        statButton = findViewById(R.id.statButton);
        feedbackButton=findViewById(R.id.feedBackButton);

        // Set the event listeners for our interactive widgets
        OnClickListener buttonEventListener = new ButtonListener();

        checkButton.setOnClickListener( buttonEventListener );
        statButton.setOnClickListener(buttonEventListener);
        feedbackButton.setOnClickListener(buttonEventListener);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        //to save status when tabbed out of the program
        savedPrefs=getSharedPreferences("TeamDataPrefs",MODE_PRIVATE);

    }
    @Override
    public void onPause() {
        // Save the teamNumber and dropdown list variables
        Editor prefsEditor = savedPrefs.edit();
        prefsEditor.putString( "teamNumberString", teamNumber );
        prefsEditor.putInt( "season", dropdown.getSelectedItemPosition());
        prefsEditor.commit();

        // Calling the parent onPause() must be done LAST
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();

        // Load the instance variables back (or default values)
        teamNumber = savedPrefs.getString("teamNumberString", "");
        dropdown.setSelection(savedPrefs.getInt("season",0));

        // Format/update these values in our widgets
        teamNumberEditText.setText(teamNumber);
        //check if taem number is empty, if it is not empty, run the fetchdata and display results
        if (!teamNumber.equals("")) {
            {
                fetchData process = new fetchData();
                process.execute();
            }
        }
    }
    /**
     * This method will detect if any button clicks happens and respond accordingly
     */
    class ButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.checkButton) {
                teamNumber=teamNumberEditText.getText().toString();
                //if nothing entered, warn the user that it is empty and ask the user to input a team number
                if (teamNumber.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter a enter number(eg.114T)",
                            Toast.LENGTH_LONG).show();
                }
                //if user entered something, fetch the data and display
                if (!teamNumber.equals("")){
                fetchData process = new fetchData();
                process.execute();
                }

            }
            //if statButton is clicked, open the 2nd activity
            if (v.getId()==R.id.statButton){
                Intent startStatPage= new Intent(MainActivity.this,Main2Activity.class);
                startActivity(startStatPage);
            }
            //if feedBackbutton is clicked, open the feedback activity
            if(v.getId()==R.id.feedBackButton){
                Intent startFeedBackPage=new Intent(MainActivity.this,FeedBackActivity.class);
                startActivity(startFeedBackPage);
            }
        }
    }


}
