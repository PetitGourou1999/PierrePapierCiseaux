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

public class ActivityGameVariant4 extends AppCompatActivity {

    ImageButton choixPierre, choixFeuille, choixCiseaux, choixPuits;


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
        setContentView(R.layout.game_variant_4);

        elementManager = new ElementManager(this.getApplicationContext());

        choixPierre = (ImageButton) findViewById(R.id.variant4ButtonPierre);
        choixFeuille = (ImageButton) findViewById(R.id.variant4ButtonFeuille);
        choixCiseaux = (ImageButton) findViewById(R.id.variant4ButtonCiseaux);
        choixPuits = (ImageButton) findViewById(R.id.variant4ButtonPuits);

        humanChoiceImage = (ImageView) findViewById(R.id.imageViewHumanChoiceVariant4);
        botChoiceImage = (ImageView) findViewById(R.id.imageViewBotChoiceVariant4);

        humanChoiceText = (TextView) findViewById(R.id.textHumanChoiceVariant4);
        botChoiceText = (TextView) findViewById(R.id.textBotChoiceVariant4);

        botScoreText = (TextView) findViewById(R.id.texteScoreBotVariant4);
        humanScoreText = (TextView) findViewById(R.id.texteScoreHumanVariant4);

        resultRound = (TextView) findViewById(R.id.textViewResultVariant4);

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

        choixPuits.setOnClickListener(e -> {
            humanChoiceImage.setImageResource(elementManager.puits.getImageId());
            humanChoiceText.setText(elementManager.puits.getName());
            chosenElementHuman = elementManager.puits;
            makeBotChoice();
        });

    }

    private void makeBotChoice(){
        chosenElementBot = elementManager.getRandomElementVariant4();
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