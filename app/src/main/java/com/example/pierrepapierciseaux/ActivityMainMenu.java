package com.example.pierrepapierciseaux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;



public class ActivityMainMenu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button buttonGameClassique = (Button) findViewById(R.id.buttonGameClassique);
        Button buttonGameVariant4 = (Button) findViewById(R.id.buttonGame4);
        Button buttonGameVariant7 = (Button) findViewById(R.id.buttonGame7);
        Button buttonRules = (Button) findViewById(R.id.buttonRegles);
        Button buttonScoreboard = (Button) findViewById(R.id.buttonClassement);
        Button buttonDeco = (Button) findViewById(R.id.buttonDeco);

        buttonGameClassique.setOnClickListener(e -> {
            Intent intentGameClassic = new Intent(this, ActivityGameClassic.class);
            startActivity(intentGameClassic);

        });

        buttonGameVariant4.setOnClickListener(e -> {
            Intent intentGameVariant4 = new Intent(this, ActivityGameVariant4.class);
            startActivity(intentGameVariant4);

        });

        buttonGameVariant7.setOnClickListener(e -> {
            Intent intentGameVariant7 = new Intent(this, ActivityGameVariant7.class);
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
            startActivity(intentDeco);

        });
    }
}