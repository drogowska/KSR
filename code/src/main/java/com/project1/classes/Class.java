package com.project1.classes;

import com.project1.model.Dictionary;

public class Class {
    Dictionary DK1;
    Dictionary DP1;
    Dictionary DN;
    Dictionary DR1;
    Dictionary DF2;

    String label;
    String prefix;
    int P;
    int FP;
    int FN;
    int N;

    double PPV;
    double TPR;
    double F1;

    public String getLabel() {
        return label;
    }

    public Class(String label) {
        this.label = label;
        this.prefix = label.substring(0, 2);
        DK1 = new Dictionary("DK1", prefix);
        DP1 = new Dictionary("DP1", prefix);
        DN = new Dictionary("DN", prefix);
        DR1 = new Dictionary("DR1", prefix);
        DF2 = new Dictionary("DF2", prefix);
    }

    private void countPPV() {
        PPV = P / (N + FP);
    }

    private void countTPR() {
        TPR = P / (P + FN);
    }

    private void countF1() {
        F1 = 2 * (PPV * TPR) / (PPV + TPR);
    }

    public void setP(int p) {
        P = p;
    }

    public void setFP(int FP) {
        this.FP = FP;
    }

    public double getPPV() {
        return PPV;
    }

    public double getTPR() {
        return TPR;
    }

    public double getF1() {
        return F1;
    }

    public void setFN(int FN) {
        this.FN = FN;
    }

    public void setN(int n) {
        N = n;
    }

    public int getP() {
        return P;
    }

    public int getFP() {
        return FP;
    }

    public int getFN() {
        return FN;
    }

    public int getN() {
        return N;
    }

    public void setTPR(double TPR) {
        this.TPR = TPR;
    }

    public Dictionary getDic(int i) {
        switch(i) {
            case 0:
                return DK1;
            case 1:
                return DF2;
            case 2:
                return DP1;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return DN;
            case 9:
                return DR1;
        }
        return null;
    }

    public Dictionary getDK1() {
        return DK1;
    }

    public Dictionary getDP1() {
        return DP1;
    }

    public Dictionary getDN() {
        return DN;
    }

    public Dictionary getDR1() {
        return DR1;
    }

    public Dictionary getDF2() {
        return DF2;
    }
}
