package com.example.pierrepapierciseaux;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pierrepapierciseaux.data.Element;
import com.example.pierrepapierciseaux.data.ElementManager;
import com.example.pierrepapierciseaux.data.EnumGameTypes;
import com.example.pierrepapierciseaux.data.EnumResults;
import com.example.pierrepapierciseaux.data.ResultWrapper;
import com.example.pierrepapierciseaux.helpers.GameHelper;

public class ActivityGameVariant4Buttons extends AppCompatActivity {

    Button choixPierre, choixFeuille, choixCiseaux, choixPuits;


    ImageView humanChoiceImage;
    ImageView botChoiceImage;

    TextView humanChoiceText, botChoiceText, resultRound, botScoreText, humanScoreText;

    ImageView point1Human, point2Human, point3Human;
    ImageView point1Bot, point2Bot, point3Bot;

    GameHelper gameHelper;
    Element chosenElementHuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_variant_4_buttons);

        gameHelper = new GameHelper(this.getApplicationContext(), EnumGameTypes.VARIANT4);

        choixPierre = (Button) findViewById(R.id.buttonPierreVariant4);
        choixFeuille = (Button) findViewById(R.id.buttonFeuilleVariant4);
        choixCiseaux = (Button) findViewById(R.id.buttonCiseauxVariant4);
        choixPuits = (Button) findViewById(R.id.buttonPuitsVariant4);

        humanChoiceImage = (ImageView) findViewById(R.id.imageViewHumanChoiceVariant4);
        botChoiceImage = (ImageView) findViewById(R.id.imageViewBotChoiceVariant4);

        humanChoiceText = (TextView) findViewById(R.id.textHumanChoiceVariant4);
        botChoiceText = (TextView) findViewById(R.id.textBotChoiceVariant4);

        botScoreText = (TextView) findViewById(R.id.texteScoreBotVariant4);
        humanScoreText = (TextView) findViewById(R.id.texteScoreHumanVariant4);

        resultRound = (TextView) findViewById(R.id.textViewResultVariant4);

        point1Human = (ImageView) findViewById(R.id.point1Human);
        point2Human = (ImageView) findViewById(R.id.point2Human);
        point3Human = (ImageView) findViewById(R.id.point3Human);

        point1Bot = (ImageView) findViewById(R.id.point1Bot);
        point2Bot = (ImageView) findViewById(R.id.point2Bot);
        point3Bot = (ImageView) findViewById(R.id.point2Bot);

        botScoreText.setText(getString(R.string.botScore) + " " + gameHelper.botScore.toString());
        humanScoreText.setText(getString(R.string.humanScore) + " " + gameHelper.humanScore.toString());

        choixPierre.setOnClickListener(e -> {
            choiceButtonListener(gameHelper.elementManager.pierre);
        });

        choixFeuille.setOnClickListener(e -> {
            choiceButtonListener(gameHelper.elementManager.feuille);
        });

        choixCiseaux.setOnClickListener(e -> {
            choiceButtonListener(gameHelper.elementManager.ciseaux);
        });

        choixPuits.setOnClickListener(e -> {
            choiceButtonListener(gameHelper.elementManager.puits);
        });

    }

    private void choiceButtonListener(Element element) {
        humanChoiceImage.setImageResource(element.getImageId());
        humanChoiceText.setText(element.getName());
        chosenElementHuman = element;
        makeBotChoice();
    }

    private void makeBotChoice() {
        ResultWrapper resultWrapper = gameHelper.makeBotChoice(chosenElementHuman);
        botChoiceImage.setImageResource(gameHelper.chosenElementBot.getImageId());
        botChoiceText.setText(gameHelper.chosenElementBot.getName());

        resultRound.setText(resultWrapper.getTextToDisplay());
        resultRound.setBackgroundColor(resultWrapper.getColorId());

        botScoreText.setText(getString(R.string.botScore) + " " + gameHelper.botScore.toString());
        humanScoreText.setText(getString(R.string.humanScore) + " " + gameHelper.humanScore.toString());
        checkScore();
    }

    private void checkScore() {
        switch (gameHelper.humanScore) {
            case 1:
                point1Human.setImageDrawable(getDrawable(R.drawable.dot_red));
                break;
            case 2:
                point2Human.setImageDrawable(getDrawable(R.drawable.dot_red));
                break;
            case 3:
                point3Human.setImageDrawable(getDrawable(R.drawable.dot_red));
                break;
            default:
                break;
        }

        switch (gameHelper.botScore) {
            case 1:
                point1Bot.setImageDrawable(getDrawable(R.drawable.dot_red));
                break;
            case 2:
                point2Bot.setImageDrawable(getDrawable(R.drawable.dot_red));
                break;
            case 3:
                point3Bot.setImageDrawable(getDrawable(R.drawable.dot_red));
                break;
            default:
                break;
        }

        if (gameHelper.botScore == 3) {
            endGame(false, R.string.vousAvezPerdu);
        } else if (gameHelper.humanScore == 3) {
            endGame(true, R.string.vousAvezGagne);
        }
    }

    private void endGame(boolean hasWon, int textId) {
        Intent endIntent = new Intent(ActivityGameVariant4Buttons.this, ActivityGameEnd.class);
        int finalScore = gameHelper.updatePlayerScore(hasWon);
        endIntent.putExtra("resultText", getString(textId));
        endIntent.putExtra("finalScore", finalScore);
        endIntent.putExtra("gameType", EnumGameTypes.VARIANT4.getValue());
        startActivity(endIntent);
    }


}