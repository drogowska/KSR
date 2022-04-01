package com.project1.metrics;

import com.project1.model.Feature;
import com.project1.model.VectorOfCharacteristics;

public abstract class Metric {

    public abstract double count(VectorOfCharacteristics a, VectorOfCharacteristics b);

    public double W(Feature a, Feature b) {
        if(a.isText() && b.isText())
            return 1 - M4.countSimilarity(a.getText(), b.getText());
        return a.getValue() - b.getValue();
    }
}
