package com.project1.model;

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
    private List<Feature> features = new ArrayList<>();

    public VectorOfCharacteristics() {
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

//    public double getNs(int i) {
//        return Ns.get(i);
//    }
//
//    public String getK1() {
//        return K1;
//    }
//
//    public void setK1(String k1) {
//        K1 = k1;
//    }
//
//    public String getF2() {
//        return F2;
//    }
//
//    public void setF2(String f2) {
//        F2 = f2;
//    }
//
//    public String getP1() {
//        return P1;
//    }
//
//    public void setP1(String p1) {
//        P1 = p1;
//    }
//
//    public long getN1() {
//        return N1;
//    }
//
//    public void setN1(long n1) {
//        N1 = n1;
//        Ns.set(0, Double.valueOf(n1));
//    }
//
//    public long getN2() {
//        return N2;
//    }
//
//    public void setN2(long n2) {
//        N2 = n2;
//        Ns.set(1, (double) n2);
//    }
//
//    public long getN3() {
//        return N3;
//    }
//
//    public void setN3(long n3) {
//        N3 = n3;
//        Ns.set(2, (double) n3);
//    }
//
//    public long getN4() {
//        return N4;
//    }
//
//    public void setN4(long n4) {
//        N4 = n4;
//        Ns.set(3, Double.valueOf(n4));
//    }
//
//    public long getN5() {
//        return N5;
//    }
//
//    public void setN5(long n5) {
//        N5 = n5;
//        Ns.set(4, Double.valueOf(n5));
//    }
//
//    public long getN6() {
//        return N6;
//    }
//
//    public void setN6(long n6) {
//        N6 = n6;
//        Ns.set(5, (double) n6);
//    }
//
//    public String getR1() {
//        return R1;
//    }
//
//    public void setR1(String r1) {
//        R1 = r1;
//    }
//
//    public String getL1() {
//        return L1;
//    }
//
//    public void setL1(String l1) {
//        L1 = l1;
//    }
}
