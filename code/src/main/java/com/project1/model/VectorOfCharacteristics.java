package com.project1.model;

import com.project1.classes.CountryClass;

import java.util.ArrayList;
import java.util.List;

public class VectorOfCharacteristics {

//    private String K1;
//    private String F2;
//    private String P1;
//    private List<Double> Ns = new ArrayList<>();
//    private long N1;
//    private long N2;
//    private long N3;
//    private long N4;
//    private long N5;
//    private long N6;
//    private String R1;
//    private String L1;
    private CountryClass label;
    private List<Feature> features = new ArrayList<>();

    public void setLabel(CountryClass label) {
        this.label = label;
    }

    public CountryClass getLabel() {
        return label;
    }

    public VectorOfCharacteristics() {
        for (int i = 0; i < 11; i++)
            features.add(new Feature());
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

    public VectorOfCharacteristics(String k1, String f2, String p1, long n1, long n2, long n3, long n4, long n5, long n6, String r1, String l1) {
//        K1 = k1;
//        F2 = f2;
//        P1 = p1;
//        N1 = n1;
//        N2 = n2;
//        N3 = n3;
//        N4 = n4;
//        N5 = n5;
//        N6 = n6;
//        R1 = r1;
//        L1 = l1;

    }

}
