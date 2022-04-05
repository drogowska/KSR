package com.project1.model;

import java.util.ArrayList;
import java.util.List;

public class VectorOfCharacteristics {

    private String label;
    private final List<Feature> features = new ArrayList<>();

    public VectorOfCharacteristics() {
        for (int i = 0; i < 11; i++)
            features.add(new Feature());
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(int i, double value) {
        features.get(i).setValue(value);
    }

    public void setFeatures(int i, String value) {
        features.get(i).setText(value);
    }

}
