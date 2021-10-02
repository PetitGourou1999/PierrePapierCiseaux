package com.example.pierrepapierciseaux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ActivityGameEnd extends AppCompatActivity {

    private Button revanche;
    private Button retourMenu;

    private TextView resultText;
    private TextView scoreText;

    private String extraText;
    private int extraScore;
    private String extraType;

    private void getUIElements() {
        resultText = findViewById(R.id.textViewResultEnd);
        scoreText = findViewById(R.id.textViewScoreEnd);

        revanche = findViewById(R.id.buttonRevanche);
        retourMenu = findViewById(R.id.buttonRetourMenuPrincipal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        getUIElements();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            extraText = extras.getString("resultText");
            extraScore = extras.getInt("finalScore");
            extraType = extras.getString("gameType");

            resultText.setText(extraText);
            scoreText.setText(getString(R.string.generalScore) + " " + String.valueOf(extraScore));
        } else {
            extraText = "";
            extraScore = 0;
            extraType = "";
        }

        retourMenu.setOnClickListener(e -> {
            startActivity(new Intent(this, ActivityMainMenu.class));
        });

        revanche.setOnClickListener(e->{
            switch (extraType){
                case "Classic":
                    startActivity(new Intent(this, ActivityGameClassicButtons.class));
                    break;
                case "Variant4":
                    startActivity(new Intent(this, ActivityGameVariant4Buttons.class));
                    break;
                case "Variant7":
                    startActivity(new Intent(this, ActivityGameVariant7Buttons.class));
                    break;
                default:
                    startActivity(new Intent(this, ActivityMainMenu.class));
                    break;
            }
        });
    }
}