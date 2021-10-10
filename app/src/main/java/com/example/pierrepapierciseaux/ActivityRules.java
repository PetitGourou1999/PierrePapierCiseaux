package com.example.pierrepapierciseaux;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activité où sont affichées les règles du jeu
 */
public class ActivityRules extends AppCompatActivity {

    /*UI*/
    private TextView title;

    private TextView txtRules1;
    private TextView txtRules2;
    private TextView txtRules3;
    private TextView txtRules4;
    private TextView txtRules5;

    private ImageView imageViewRules;

    private Button precedentButton;
    private Button suivantButton;

    /*Métier*/
    private int stepCounter = 1;
    private boolean fromInscription = false;

    /**
     * Récupération des UI
     */
    private void getElements() {
        title = findViewById(R.id.titleRules);
        txtRules1 = findViewById(R.id.textViewRules1);
        txtRules2 = findViewById(R.id.textViewRules2);
        txtRules3 = findViewById(R.id.textViewRules3);
        txtRules4 = findViewById(R.id.textViewRules4);
        txtRules5 = findViewById(R.id.textViewRules5);

        imageViewRules = findViewById(R.id.imageViewRules);

        precedentButton = findViewById(R.id.buttonPrecedentRules);
        suivantButton = findViewById(R.id.buttonSuivantRules);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rules);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromInscription = extras.getBoolean("fromInscription");
        } else {
            fromInscription = false;
        }

        getElements();

        displayStep();

        precedentButton.setOnClickListener(e -> {
            if (stepCounter >= 0)
                stepCounter--;
            displayStep();
        });

        suivantButton.setOnClickListener(e -> {
            if (stepCounter <= 6)
                stepCounter++;
            displayStep();
        });

    }

    /**
     * Affichage des éléments qui correspondent à la bonne étape de lecture des règles
     */
    private void displayStep() {
        switch (stepCounter) {
            case 0:
            case 6:
                if (fromInscription) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    this.finish();
                }
                break;
            case 1: //Affichage règles générales
                precedentButton.setText(R.string.retour);
                displayTexts(true);
                txtRules1.setText(R.string.rules1);
                txtRules2.setText(R.string.rules2);
                txtRules3.setText(R.string.rules3);
                txtRules4.setText(R.string.rules4);
                txtRules5.setText("");
                title.setText(R.string.titleRules);
                break;
            case 2: //Affichage règles du score
                precedentButton.setText(R.string.precedent);
                suivantButton.setText(R.string.suivant);
                displayTexts(true);
                txtRules1.setText(R.string.rulesScore1);
                txtRules2.setText(R.string.rulesScore2);
                txtRules3.setText(R.string.rulesScore3);
                txtRules4.setText(R.string.rulesScore4);
                txtRules5.setText(R.string.rulesScore5);
                title.setText(R.string.titleRulesScore);
                break;
            case 3: //Affichage règles jeu à 3
                precedentButton.setText(R.string.precedent);
                title.setText(R.string.titleRulesClassique);
                suivantButton.setText(R.string.suivant);
                displayTexts(false);
                imageViewRules.setImageDrawable(getDrawable(R.drawable.rules_3));
                break;
            case 4: //Affichage règles jeu à 4
                precedentButton.setText(R.string.precedent);
                title.setText(R.string.titleRules4);
                suivantButton.setText(R.string.suivant);
                displayTexts(false);
                imageViewRules.setImageDrawable(getDrawable(R.drawable.rules_4));
                break;
            case 5: //Affichage règles jeu à 7
                precedentButton.setText(R.string.precedent);
                title.setText(R.string.titleRules7);
                displayTexts(false);
                imageViewRules.setImageDrawable(getDrawable(R.drawable.rules_7));
                suivantButton.setText(R.string.termine);
                break;
        }
    }

    /**
     * Affichage ou non des TextView
     *
     * @param visible booléen qui spécifie si on doit afficher ou non les textes
     */
    private void displayTexts(boolean visible) {
        if (visible) {
            txtRules1.setVisibility(View.VISIBLE);
            txtRules2.setVisibility(View.VISIBLE);
            txtRules3.setVisibility(View.VISIBLE);
            txtRules4.setVisibility(View.VISIBLE);
            txtRules5.setVisibility(View.VISIBLE);
            imageViewRules.setVisibility(View.GONE);
        } else {
            txtRules1.setVisibility(View.GONE);
            txtRules2.setVisibility(View.GONE);
            txtRules3.setVisibility(View.GONE);
            txtRules4.setVisibility(View.GONE);
            txtRules5.setVisibility(View.GONE);
            imageViewRules.setVisibility(View.VISIBLE);
        }
    }
}