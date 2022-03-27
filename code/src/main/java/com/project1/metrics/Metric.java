package com.project1.metrics;

import com.project1.model.VectorOfCharacteristics;

public interface Metric {

    public  double count(VectorOfCharacteristics a, VectorOfCharacteristics b);
}
