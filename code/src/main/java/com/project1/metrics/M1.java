package com.project1.metrics;

import com.project1.model.VectorOfCharacteristics;

public class M1 extends Metric {

    @Override
    public double count(VectorOfCharacteristics a, VectorOfCharacteristics b) {
        double result = 0;
        for(int i = 0; i < 11; i++) {
            result += Math.pow(W(a.getFeatures().get(i), b.getFeatures().get(i)),2) ;
        }
        return Math.sqrt(result);
    }
}
