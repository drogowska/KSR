package com.project1.modules;


import com.project1.metrics.Metric;
import com.project1.model.VectorOfCharacteristics;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KnnAlgorithm {

    private final int k;
    List<VectorOfCharacteristics> docs;
    Metric metric;

    public KnnAlgorithm(int k, List<VectorOfCharacteristics> docs, Metric metric) {
        this.k = k;
        this.docs = docs;
        this.metric = metric;
    }

    public void run(VectorOfCharacteristics object) {
        HashMap<VectorOfCharacteristics, Double> distances = new HashMap<>();
        docs.forEach(doc -> {
            distances.put(doc, metric.count(object, doc));
        });

        Map<String, Long> res = distances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .limit(k)
                .map(VectorOfCharacteristics::getLabel)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        object.setLabel(Collections.max(res.entrySet(), Map.Entry.comparingByValue()).getKey());
    }
}
