package com.example.pierrepapierciseaux;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pierrepapierciseaux.data.Utilisateur;
import com.google.firebase.auth.FirebaseAuth;


public class ActivityMainMenu extends AppCompatActivity {

    /*Métier*/
    private String userName;
    private int userScore;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        mAuth = FirebaseAuth.getInstance();

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("preferences-key-name", MODE_PRIVATE);
        userName = prefs.getString("userName", null);
        userScore = prefs.getInt("userScore", 0);

        Button buttonGameClassique = (Button) findViewById(R.id.buttonGameClassique);
        Button buttonGameVariant4 = (Button) findViewById(R.id.buttonGame4);
        Button buttonGameVariant7 = (Button) findViewById(R.id.buttonGame7);
        Button buttonRules = (Button) findViewById(R.id.buttonRegles);
        Button buttonScoreboard = (Button) findViewById(R.id.buttonClassement);
        Button buttonDeco = (Button) findViewById(R.id.buttonDeco);

        TextView usernameText = (TextView) findViewById(R.id.textViewUsername);
        TextView scoreText = (TextView) findViewById(R.id.textViewScore);

        buttonGameClassique.setOnClickListener(e -> {
            Intent intentGameClassic = new Intent(this, ActivityGameClassicButtons.class);
            startActivity(intentGameClassic);
        });

        buttonGameVariant4.setOnClickListener(e -> {
            Intent intentGameVariant4 = new Intent(this, ActivityGameVariant4Buttons.class);
            startActivity(intentGameVariant4);
        });

        buttonGameVariant7.setOnClickListener(e -> {
            Intent intentGameVariant7 = new Intent(this, ActivityGameVariant7Buttons.class);
            startActivity(intentGameVariant7);
        });

        buttonRules.setOnClickListener(e -> {
            Intent intentRules = new Intent(this, ActivityRules.class);
            startActivity(intentRules);
        });

        buttonScoreboard.setOnClickListener(e -> {
            Intent intentScoreboard = new Intent(this, ActivityScoreboard.class);
            startActivity(intentScoreboard);
        });

        buttonDeco.setOnClickListener(e -> {
            Intent intentDeco = new Intent(this, LoginActivity.class);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();
            mAuth.signOut();
            startActivity(intentDeco);
        });

        if (userName != null) {
            usernameText.setText(getString(R.string.humanScore) + " " + userName);
            scoreText.setText(getString(R.string.generalScore) + " " + userScore);
        }


    }
}