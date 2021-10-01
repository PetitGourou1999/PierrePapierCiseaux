package com.example.pierrepapierciseaux.data;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Objects;

public class Element {

    private String name;
    private ArrayList<Element> weaknesses;
    private int imageId;

    public Element(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Element> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(Element... weaknesses) {
        this.weaknesses = new ArrayList<Element>();
        for (int i = 0; i < weaknesses.length; i++) {
            this.weaknesses.add(weaknesses[i]);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return name.equals(element.name);
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public EnumResults checkWeakness(Element otherElement){

        if(otherElement.equals(this)){
            return EnumResults.TIE;
        }else if(this.weaknesses.contains(otherElement)){
            return EnumResults.DEFEAT;
        }else{
            return  EnumResults.VICTORY;
        }
    }

}
