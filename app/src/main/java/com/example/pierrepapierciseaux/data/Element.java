package com.example.pierrepapierciseaux.data;

import java.util.ArrayList;
import java.util.Objects;

public class Element {

    private String name;
    private ArrayList<Element> weaknesses;
    private ArrayList<Element> strengths;

    public Element(String name) {
        this.name = name;
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

    public ArrayList<Element> getStrengths() {
        return strengths;
    }

    public void setStrengths(Element... strengths) {
        this.strengths = new ArrayList<Element>();
        for (int i = 0; i < strengths.length; i++) {
            this.strengths.add(strengths[i]);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return name.equals(element.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
