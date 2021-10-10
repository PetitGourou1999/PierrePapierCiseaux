package com.example.pierrepapierciseaux.helpers;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pierrepapierciseaux.R;
import com.example.pierrepapierciseaux.data.Element;
import com.example.pierrepapierciseaux.data.ElementManager;
import com.example.pierrepapierciseaux.data.EnumGameTypes;
import com.example.pierrepapierciseaux.data.EnumResults;
import com.example.pierrepapierciseaux.data.ResultWrapper;

/**
 * Classe helper pour la gestion du jeu côté "métier"
 */
public class GameHelper {

    private Context appContext;
    private EnumGameTypes gameType;

    public Integer humanScore;
    public Integer botScore;

    public Element chosenElementBot;

    public ElementManager elementManager;

    /**
     * Constructeur
     *
     * @param appContext contexte de l'application
     * @param gameType   type de jeu (classique, puissance 4, puissance 7)
     */
    public GameHelper(Context appContext, EnumGameTypes gameType) {
        this.appContext = appContext;
        this.gameType = gameType;
        humanScore = 0;
        botScore = 0;
        elementManager = new ElementManager(this.appContext);
    }

    /**
     * Méthode pour choisir un élément en hasard dans la liste et gérer le résulat de la
     * manche par rapport à l'élément choisi par le joueur
     *
     * @param chosenElementHuman l'élément choisi par le joueur
     * @return le résutat de la manche
     */
    public ResultWrapper makeBotChoice(Element chosenElementHuman) {
        switch (this.gameType) {
            case CLASSIC:
                chosenElementBot = elementManager.getRandomElementClassic();
                break;
            case VARIANT4:
                chosenElementBot = elementManager.getRandomElementVariant4();
                break;
            case VARIANT7:
                chosenElementBot = elementManager.getRandomElementVariant7();
                break;
        }

        EnumResults result = chosenElementHuman.checkWeakness(chosenElementBot);

        switch (result) {
            case TIE:
                return new ResultWrapper((appContext.getString(R.string.resultRound)
                        + " " + appContext.getString(R.string.resultTie)),
                        appContext.getColor(R.color.turquoise));
            case DEFEAT:
                botScore++;
                return new ResultWrapper((appContext.getString(R.string.resultRound)
                        + " " + appContext.getString(R.string.resultDefeat)),
                        appContext.getColor(R.color.red));
            case VICTORY:
                humanScore++;
                return new ResultWrapper((appContext.getString(R.string.resultRound)
                        + " " + appContext.getString(R.string.resultVictory)),
                        appContext.getColor(R.color.green));
        }

        return new ResultWrapper((appContext.getString(R.string.resultRound)
                + " " + appContext.getString(R.string.resultTie)),
                appContext.getColor(R.color.turquoise));
    }

    /**
     * Mise à jour du socre de l'utilisateur en focntion du résultat de la partie et du type de jeu
     * @param hasWon booléen pour savoir si le joueur a gagné ou non
     * @return le npouveau score du joueur
     */
    public int updatePlayerScore(boolean hasWon) {
        SharedPreferences prefs = appContext.getSharedPreferences("preferences-key-name", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int userScore = prefs.getInt("userScore", 0);
        editor.remove("userScore");

        editor.commit();

        switch (gameType) {
            case CLASSIC:
                if (hasWon)
                    userScore += 3;
                else
                    userScore -= 1;
                break;
            case VARIANT4:
                if (hasWon)
                    userScore += 5;
                else
                    userScore -= 2;
                break;
            case VARIANT7:
                if (hasWon)
                    userScore += 8;
                else
                    userScore -= 3;
                break;
        }

        if (userScore < 0)
            userScore = 0;

        editor.putInt("userScore", userScore);
        editor.commit();

        updateFirebase(userScore);

        return userScore;
    }

    /**
     * Update du score sur Firebase
     * @param userScore le nouveau score de l'utilisateur
     */
    private void updateFirebase(int userScore) {
        FirebaseHelper.updateFirebase(appContext, userScore);
    }

}
