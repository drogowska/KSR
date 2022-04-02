package com.project1.modules;

import com.project1.model.Article;
import com.project1.metrics.Metric;
import com.project1.model.VectorOfCharacteristics;

import java.util.ArrayList;
import java.util.List;

public class Classifier {

    KnnAlgorithm knnAlgorithm;

    public void classify(int k, List<Article> testingGroup, Metric metric, List<Article> trainingGroup) {
        List<VectorOfCharacteristics> testingVectors = new ArrayList();
        trainingGroup.forEach(doc -> {
            testingVectors.add(doc.getVectorOfCharacteristics());
        });
        knnAlgorithm = new KnnAlgorithm(k, testingVectors, metric);
        testingGroup.forEach(article -> knnAlgorithm.run(article.getVectorOfCharacteristics()));
    }
}
