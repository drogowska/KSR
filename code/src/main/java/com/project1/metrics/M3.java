package com.project1.metrics;

import com.project1.model.VectorOfCharacteristics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class M3 extends Metric {
    @Override
    public double count(VectorOfCharacteristics a, VectorOfCharacteristics b) {
        List<Double> list = new ArrayList();
        for(int i = 0; i < 11; i++) {
            list.add(W(a.getFeatures().get(i), b.getFeatures().get(i)));
        }
        return Collections.max(list);
    }
}
