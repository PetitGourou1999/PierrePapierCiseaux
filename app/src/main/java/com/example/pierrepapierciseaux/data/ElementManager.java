package com.example.pierrepapierciseaux.data;

import android.content.res.Resources;

import com.example.pierrepapierciseaux.R;

public class ElementManager {

    public Element pierre;
    public Element feuille;
    public Element ciseaux;
    public Element feu;
    public Element eau;
    public Element eponge;
    public Element air;

    public ElementManager() {
        initElements();
    }

    public void initElements() {
        pierre = new Element(Resources.getSystem().getString(R.string.pierre));
        feuille = new Element(Resources.getSystem().getString(R.string.feuille));
        ciseaux = new Element(Resources.getSystem().getString(R.string.ciseaux));
        feu = new Element(Resources.getSystem().getString(R.string.feu));
        eau = new Element(Resources.getSystem().getString(R.string.eau));
        eponge = new Element(Resources.getSystem().getString(R.string.eponge));
        air = new Element(Resources.getSystem().getString(R.string.air));

        pierre.setStrengths(feu, ciseaux, eponge);
        feuille.setStrengths(air, eau, pierre);
        ciseaux.setStrengths(eponge, feuille, air);
        feu.setStrengths(ciseaux, eponge, feuille);
        eau.setStrengths(pierre, feu, ciseaux);
        eponge.setStrengths(feuille, air, eau);
        air.setStrengths(eau, pierre, feu);

        pierre.setWeaknesses(eau, air, feuille);
        feuille.setWeaknesses(feu, ciseaux, eponge);
        ciseaux.setWeaknesses(feu, pierre, eau);
        feu.setWeaknesses(eau, air, pierre);
        eau.setWeaknesses(air, feuille, eponge);
        eponge.setWeaknesses(ciseaux, feu, pierre);
        air.setWeaknesses(feuille, eponge, ciseaux);

    }
}
