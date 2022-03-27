package com.project1.metrics;

import com.project1.model.VectorOfCharacteristics;

public abstract class Metric {

    public abstract double count(VectorOfCharacteristics a, VectorOfCharacteristics b);
}
