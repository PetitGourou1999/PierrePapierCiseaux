package com.example.pierrepapierciseaux;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pierrepapierciseaux.data.Element;
import com.example.pierrepapierciseaux.data.ElementManager;
import com.example.pierrepapierciseaux.data.EnumResults;

public class ActivityGameClassicButtons extends AppCompatActivity {

    Button choixPierre;
    Button choixFeuille;
    Button choixCiseaux;

    ImageView humanChoiceImage;
    ImageView botChoiceImage;

    ImageView point1Human, point2Human, point3Human;
    ImageView point1Bot, point2Bot, point3Bot;

    TextView humanChoiceText, botChoiceText, resultRound, botScoreText, humanScoreText;

    ElementManager elementManager;

    Element chosenElementBot;
    Element chosenElementHuman;

    Integer humanScore;
    Integer botScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_classic_buttons);

        elementManager = new ElementManager(this.getApplicationContext());

        choixPierre = (Button) findViewById(R.id.buttonPierreClassic);
        choixFeuille = (Button) findViewById(R.id.buttonFeuilleClassic);
        choixCiseaux = (Button) findViewById(R.id.buttonCiseauxClassic);

        humanChoiceImage = (ImageView) findViewById(R.id.imageViewHumanChoiceClassic);
        botChoiceImage = (ImageView) findViewById(R.id.imageViewBotChoiceClassic);

        humanChoiceText = (TextView) findViewById(R.id.textHumanChoiceClassic);
        botChoiceText = (TextView) findViewById(R.id.textBotChoiceClassic);

        botScoreText = (TextView) findViewById(R.id.texteScoreBotClassic);
        humanScoreText = (TextView) findViewById(R.id.texteScoreHumanClassic);

        resultRound = (TextView) findViewById(R.id.textViewResultClassic);

        point1Human = (ImageView) findViewById(R.id.point1Human);
        point2Human = (ImageView) findViewById(R.id.point2Human);
        point3Human = (ImageView) findViewById(R.id.point3Human);

        point1Bot = (ImageView) findViewById(R.id.point1Bot);
        point2Bot = (ImageView) findViewById(R.id.point2Bot);
        point3Bot = (ImageView) findViewById(R.id.point2Bot);


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

    }

    private void makeBotChoice() {
        chosenElementBot = elementManager.getRandomElementClassic();
        botChoiceImage.setImageResource(chosenElementBot.getImageId());
        botChoiceText.setText(chosenElementBot.getName());
        EnumResults result = chosenElementHuman.checkWeakness(chosenElementBot);

        switch (result) {
            case TIE:
                resultRound.setText(getString(R.string.resultRound) + " " + getString(R.string.resultTie));
                resultRound.setBackgroundColor(getColor(R.color.turquoise));
                break;
            case DEFEAT:
                botScore++;
                resultRound.setText(getString(R.string.resultRound) + " " + getString(R.string.resultDefeat));
                resultRound.setBackgroundColor(getColor(R.color.red));
                break;
            case VICTORY:
                humanScore++;
                resultRound.setText(getString(R.string.resultRound) + " " + getString(R.string.resultVictory));
                resultRound.setBackgroundColor(getColor(R.color.green));
                break;
        }
        botScoreText.setText(getString(R.string.botScore) + " " + botScore.toString());
        humanScoreText.setText(getString(R.string.humanScore) + " " + humanScore.toString());
        checkScore();
    }

    private void checkScore() {
        switch (humanScore) {
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

        switch (botScore) {
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

        if (botScore == 3) {
            Toast.makeText(this.getApplicationContext(), "Défaite", Toast.LENGTH_LONG).show();
            resetScore();
        } else if (humanScore == 3) {
            Toast.makeText(this.getApplicationContext(), "Victoire", Toast.LENGTH_LONG).show();
            resetScore();
        }
    }

    private void resetScore(){
        botScore = 0;
        humanScore = 0;
        botScoreText.setText(getString(R.string.botScore) + " " + botScore.toString());
        humanScoreText.setText(getString(R.string.humanScore) + " " + humanScore.toString());

        point1Bot.setImageDrawable(getDrawable(R.drawable.dot_black));
        point2Bot.setImageDrawable(getDrawable(R.drawable.dot_black));
        point3Bot.setImageDrawable(getDrawable(R.drawable.dot_black));

        point1Human.setImageDrawable(getDrawable(R.drawable.dot_black));
        point2Human.setImageDrawable(getDrawable(R.drawable.dot_black));
        point3Human.setImageDrawable(getDrawable(R.drawable.dot_black));
    }

}