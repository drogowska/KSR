package com.project1.model;

public class Feature {

    private boolean isText;
    private String text;
    private double value;

    public Feature() {
        this.text = null;
        this.value = 0;
        isText = false;
    }

    public String getText() {
        return text;
    }

    public boolean isText() {
        return isText;
    }

    public void setText(String text) {
        this.text = text;
        isText = true;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
