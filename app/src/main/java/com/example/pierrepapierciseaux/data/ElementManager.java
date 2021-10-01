package com.example.pierrepapierciseaux.data;

import android.content.Context;
import android.content.res.Resources;

import com.example.pierrepapierciseaux.R;

import java.sql.Array;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ElementManager {

    public Element pierre;
    public Element feuille;
    public Element ciseaux;
    public Element puits;
    public Element feu;
    public Element eau;
    public Element eponge;
    public Element air;

    public ArrayList<Element> classicList;
    public ArrayList<Element> variant4List;
    public ArrayList<Element> variant7List;

    public ElementManager(Context context) {
        initElements(context);
        initList();
    }

    private void initList(){
        classicList = new ArrayList<Element>(Arrays.asList(pierre, feuille, ciseaux));
        variant4List = new ArrayList<Element>(Arrays.asList(pierre, feuille, ciseaux, puits));
        variant7List = new ArrayList<Element>(Arrays.asList(pierre, feuille, ciseaux, feu, eau, air, eponge));
    }

    private void initElements(Context context) {
        pierre = new Element(context.getString(R.string.pierre), R.drawable.pierre);
        feuille = new Element(context.getString(R.string.feuille),R.drawable.feuille);
        ciseaux = new Element(context.getString(R.string.ciseaux),R.drawable.ciseaux);
        puits = new Element(context.getString(R.string.puits),R.drawable.puits);
        feu = new Element(context.getString(R.string.feu),R.drawable.feu);
        eau = new Element(context.getString(R.string.eau),R.drawable.eau);
        eponge = new Element(context.getString(R.string.eponge),R.drawable.eponge);
        air = new Element(context.getString(R.string.air),R.drawable.air);

        pierre.setWeaknesses(eau, air, feuille,puits);
        feuille.setWeaknesses(feu, ciseaux, eponge);
        ciseaux.setWeaknesses(feu, pierre, eau, puits);
        puits.setWeaknesses(feuille);
        feu.setWeaknesses(eau, air, pierre);
        eau.setWeaknesses(air, feuille, eponge);
        eponge.setWeaknesses(ciseaux, feu, pierre);
        air.setWeaknesses(feuille, eponge, ciseaux);

    }

    private Element getRandomElement(ArrayList<Element> elements){

        Random rand = new Random();
        Element randomElement = elements.get(rand.nextInt(elements.size()));
        return randomElement;
    }

    public Element getRandomElementClassic(){
        return getRandomElement(this.classicList);
    }

    public Element getRandomElementVariant4(){
        return getRandomElement(this.variant4List);
    }

    public Element getRandomElementVariant7(){
        return getRandomElement(this.variant7List);
    }


}
