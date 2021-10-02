package com.example.pierrepapierciseaux.data;

public class ResultWrapper {

    private String textToDisplay;
    private int colorId;

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
