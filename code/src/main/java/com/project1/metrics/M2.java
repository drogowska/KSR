package com.project1.metrics;

import com.project1.model.VectorOfCharacteristics;

public class M2 extends Metric {
    @Override
    public double count(VectorOfCharacteristics a, VectorOfCharacteristics b) {
        double result = 0;
        for(int i = 0; i < 11; i++) {
            result += Math.abs(W(a.getFeatures().get(i), b.getFeatures().get(i))) ;
        }
        return result;
    }
}
