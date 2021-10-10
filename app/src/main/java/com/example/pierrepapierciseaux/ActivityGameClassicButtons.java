package com.example.pierrepapierciseaux;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pierrepapierciseaux.data.Element;
import com.example.pierrepapierciseaux.data.EnumGameTypes;
import com.example.pierrepapierciseaux.data.ResultWrapper;
import com.example.pierrepapierciseaux.helpers.GameHelper;

/**
 * Activité pour le mode de jeu classique
 */
public class ActivityGameClassicButtons extends AppCompatActivity {

    /*UI*/
    Button choixPierre;
    Button choixFeuille;
    Button choixCiseaux;

    ImageButton buttonRules;

    ImageView humanChoiceImage;
    ImageView botChoiceImage;

    ImageView point1Human, point2Human, point3Human;
    ImageView point1Bot, point2Bot, point3Bot;

    TextView humanChoiceText, botChoiceText, resultRound, botScoreText, humanScoreText;

    /*Métier*/
    GameHelper gameHelper;
    Element chosenElementHuman;

    /**
     * Récupération des éléments de l'UI
     */
    private void getUIElements() {
        choixPierre = (Button) findViewById(R.id.buttonPierreClassic);
        choixFeuille = (Button) findViewById(R.id.buttonFeuilleClassic);
        choixCiseaux = (Button) findViewById(R.id.buttonCiseauxClassic);
        buttonRules = (ImageButton) findViewById(R.id.imageButtonRules);

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_classic_buttons);

        gameHelper = new GameHelper(this.getApplicationContext(), EnumGameTypes.CLASSIC);

        getUIElements();

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

        buttonRules.setOnClickListener(e -> {
            Intent intent = new Intent(this, ActivityRules.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, 0);
        });

    }

    /**
     * Méthode appelée au clic sur un des boutons qui représentent les éléments
     *
     * @param element l'élément représenté par le bouton sur lequel ke joueur a cliqué
     */
    private void choiceButtonListener(Element element) {
        humanChoiceImage.setImageResource(element.getImageId());
        humanChoiceText.setText(element.getName());
        chosenElementHuman = element;
        makeBotChoice();
    }

    /**
     * Réalisation du choix de l'IA
     */
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

    /**
     * Vérification du score et affichage des manches remportées par chacun
     */
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

    /**
     * Gestion de fin de partie : redirectiopn vers l'activité correspondante
     *
     * @param hasWon booléen pour savoir si le joueur a gagné
     * @param textId l'id du texte à afficher sur cette nouvelle page
     */
    private void endGame(boolean hasWon, int textId) {
        Intent endIntent = new Intent(ActivityGameClassicButtons.this, ActivityGameEnd.class);
        int finalScore = gameHelper.updatePlayerScore(hasWon);
        endIntent.putExtra("resultText", getString(textId));
        endIntent.putExtra("finalScore", finalScore);
        endIntent.putExtra("gameType", EnumGameTypes.CLASSIC.getValue());
        startActivity(endIntent);
    }

}