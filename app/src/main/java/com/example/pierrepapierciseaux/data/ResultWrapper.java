package com.example.pierrepapierciseaux.data;

/**
 * Wrapper pour le résutat d'une manche
 */
public class ResultWrapper {

    private String textToDisplay;
    private int colorId;

    /**
     * Constructeur
     *
     * @param textToDisplay le texte à afficher selon le résultat
     * @param colorId       la couleur que prendra le bandeau dans l'UI
     */
    public ResultWrapper(String textToDisplay, int colorId) {
        this.textToDisplay = textToDisplay;
        this.colorId = colorId;
    }

    public String getTextToDisplay() {
        return textToDisplay;
    }

    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
