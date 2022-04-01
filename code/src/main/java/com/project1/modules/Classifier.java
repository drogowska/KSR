package com.project1.modules;

import com.project1.model.Article;
import com.project1.metrics.Metric;
import com.project1.model.VectorOfCharacteristics;

import java.util.ArrayList;
import java.util.List;

public class Classifier {

    KnnAlgorithm knnAlgorithm;

    public void classify(int k, List<Article> testingGroup, Metric metric, List<Article> articles) {
        List<VectorOfCharacteristics> testingVectors = new ArrayList();
        testingGroup.forEach(doc -> {
            testingVectors.add(doc.getVectorOfCharacteristics());
        });
        knnAlgorithm = new KnnAlgorithm(k, testingVectors, metric);
        articles.forEach(article -> knnAlgorithm.run(article.getVectorOfCharacteristics()));
    }
}
