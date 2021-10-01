package com.example.pierrepapierciseaux;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pierrepapierciseaux.data.Utilisateur;


public class ActivityMainMenu extends AppCompatActivity {

    Utilisateur myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myUser = (Utilisateur) extras.getSerializable("user");
        }

        Button buttonGameClassique = (Button) findViewById(R.id.buttonGameClassique);
        Button buttonGameVariant4 = (Button) findViewById(R.id.buttonGame4);
        Button buttonGameVariant7 = (Button) findViewById(R.id.buttonGame7);
        Button buttonRules = (Button) findViewById(R.id.buttonRegles);
        Button buttonScoreboard = (Button) findViewById(R.id.buttonClassement);
        Button buttonDeco = (Button) findViewById(R.id.buttonDeco);

        TextView usernameText = (TextView) findViewById(R.id.textViewUsername);
        TextView scoreText = (TextView) findViewById(R.id.textViewScore);

        buttonGameClassique.setOnClickListener(e -> {
            Intent intentGameClassic = new Intent(this, ActivityGameClassic.class);
            intentGameClassic.putExtra("user", myUser);
            startActivity(intentGameClassic);
        });

        buttonGameVariant4.setOnClickListener(e -> {
            Intent intentGameVariant4 = new Intent(this, ActivityGameVariant4.class);
            intentGameVariant4.putExtra("user", myUser);
            startActivity(intentGameVariant4);
        });

        buttonGameVariant7.setOnClickListener(e -> {
            Intent intentGameVariant7 = new Intent(this, ActivityGameVariant7.class);
            intentGameVariant7.putExtra("user", myUser);
            startActivity(intentGameVariant7);
        });

        buttonRules.setOnClickListener(e -> {
            Intent intentRules = new Intent(this, ActivityRules.class);
            startActivity(intentRules);
        });

        buttonScoreboard.setOnClickListener(e -> {
            Intent intentScoreboard = new Intent(this, ActivityScoreboard.class);
            intentScoreboard.putExtra("user", myUser);
            startActivity(intentScoreboard);
        });

        buttonDeco.setOnClickListener(e -> {
            Intent intentDeco = new Intent(this, LoginActivity.class);
            startActivity(intentDeco);
        });

        if (myUser != null) {
            usernameText.setText(getString(R.string.humanScore) + " "
                    + myUser.getPrenom() + " " + myUser.getNom());
            scoreText.setText(getString(R.string.generalScore) + " " + myUser.getScore());
        }
    }
}