package com.example.pierrepapierciseaux;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pierrepapierciseaux.data.Element;
import com.example.pierrepapierciseaux.data.ElementManager;
import com.example.pierrepapierciseaux.data.EnumResults;

public class ActivityGameVariant7 extends AppCompatActivity {

    ImageButton choixPierre, choixFeuille, choixCiseaux, choixFeu, choixEau, choixAir, choixEponge;

    ImageView humanChoiceImage;
    ImageView botChoiceImage;

    TextView humanChoiceText, botChoiceText, resultRound, botScoreText, humanScoreText;

    ElementManager elementManager;

    Element chosenElementBot;
    Element chosenElementHuman;

    Integer humanScore;
    Integer botScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_variant_7);

        elementManager = new ElementManager(this.getApplicationContext());

        choixPierre = (ImageButton) findViewById(R.id.variant7ButtonPierre);
        choixFeuille = (ImageButton) findViewById(R.id.variant7ButtonFeuille);
        choixCiseaux = (ImageButton) findViewById(R.id.variant7ButtonCiseaux);
        choixFeu = (ImageButton) findViewById(R.id.variant7ButtonFeu);
        choixEau = (ImageButton) findViewById(R.id.variant7ButtonEau);
        choixAir = (ImageButton) findViewById(R.id.variant7ButtonAir);
        choixEponge = (ImageButton) findViewById(R.id.variant7ButtonEponge);

        humanChoiceImage = (ImageView) findViewById(R.id.imageViewHumanChoiceVariant7);
        botChoiceImage = (ImageView) findViewById(R.id.imageViewBotChoiceVariant7);

        humanChoiceText = (TextView) findViewById(R.id.textHumanChoiceVariant7);
        botChoiceText = (TextView) findViewById(R.id.textBotChoiceVariant7);

        botScoreText = (TextView) findViewById(R.id.texteScoreBotVariant7);
        humanScoreText = (TextView) findViewById(R.id.texteScoreHumanVariant7);

        resultRound = (TextView) findViewById(R.id.textViewResultVariant7);

        botScore = 0;
        humanScore = 0;

        botScoreText.setText(getString(R.string.botScore) + " " + botScore.toString());
        humanScoreText.setText(getString(R.string.humanScore) + " " + humanScore.toString());

        choixPierre.setOnClickListener(e -> {
            humanChoiceImage.setImageResource(elementManager.pierre.getImageId());
            humanChoiceText.setText(elementManager.pierre.getName());
            chosenElementHuman = elementManager.pierre;
            makeBotChoice();
        });

        choixFeuille.setOnClickListener(e -> {
            humanChoiceImage.setImageResource(elementManager.feuille.getImageId());
            humanChoiceText.setText(elementManager.feuille.getName());
            chosenElementHuman = elementManager.feuille;
            makeBotChoice();
        });

        choixCiseaux.setOnClickListener(e -> {
            humanChoiceImage.setImageResource(elementManager.ciseaux.getImageId());
            humanChoiceText.setText(elementManager.ciseaux.getName());
            chosenElementHuman = elementManager.ciseaux;
            makeBotChoice();
        });

        choixFeu.setOnClickListener(e -> {
            humanChoiceImage.setImageResource(elementManager.feu.getImageId());
            humanChoiceText.setText(elementManager.feu.getName());
            chosenElementHuman = elementManager.feu;
            makeBotChoice();
        });

        choixEau.setOnClickListener(e -> {
            humanChoiceImage.setImageResource(elementManager.eau.getImageId());
            humanChoiceText.setText(elementManager.eau.getName());
            chosenElementHuman = elementManager.eau;
            makeBotChoice();
        });

        choixAir.setOnClickListener(e -> {
            humanChoiceImage.setImageResource(elementManager.air.getImageId());
            humanChoiceText.setText(elementManager.air.getName());
            chosenElementHuman = elementManager.air;
            makeBotChoice();
        });

        choixEponge.setOnClickListener(e -> {
            humanChoiceImage.setImageResource(elementManager.eponge.getImageId());
            humanChoiceText.setText(elementManager.eponge.getName());
            chosenElementHuman = elementManager.eponge;
            makeBotChoice();
        });

    }

    private void makeBotChoice(){
        chosenElementBot = elementManager.getRandomElementVariant7();
        botChoiceImage.setImageResource(chosenElementBot.getImageId());
        botChoiceText.setText(chosenElementBot.getName());
        EnumResults result = chosenElementHuman.checkWeakness(chosenElementBot);

        switch (result){
            case TIE:
                resultRound.setText(getString(R.string.resultRound) + " " + getString(R.string.resultTie));
                break;
            case DEFEAT:
                botScore++;
                resultRound.setText(getString(R.string.resultRound) + " " + getString(R.string.resultDefeat));
                break;
            case VICTORY:
                humanScore++;
                resultRound.setText(getString(R.string.resultRound) + " " + getString(R.string.resultVictory));
                break;
        }
        botScoreText.setText(getString(R.string.botScore) + " " + botScore.toString());
        humanScoreText.setText(getString(R.string.humanScore) + " " + humanScore.toString());
        checkScore();
    }

    private void checkScore(){
        if(botScore == 3 ){
            Toast.makeText(this.getApplicationContext(),"Défaite",Toast.LENGTH_LONG).show();
            botScore = 0;
            humanScore = 0;
        }else if(humanScore == 3){
            Toast.makeText(this.getApplicationContext(),"Victoire",Toast.LENGTH_LONG).show();
            botScore = 0;
            humanScore = 0;
        }

    }
}