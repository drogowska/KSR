package com.project1.modules;

import com.project1.model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project1.Main.classes;

public class Statistics {

    final List<Article> articles;
    public Statistics(List<Article> articles) {
        this.articles = new ArrayList<>(articles);
        count();
        setN();
    }

    public double ACC() {
        int p = (int) articles.stream().filter(article -> article.getPlaces().get(0).equals(article.getVectorOfCharacteristics().getLabel())).count();
        return (double) p / articles.size();
    }

    private void count() {
        classes.forEach(clas -> {
            List<Article> docs = articles.stream().filter(article -> article.getPlaces().get(0).equals(clas.getLabel())).collect(Collectors.toList());
            clas.setP((int) docs.stream().filter(article ->
                    article.getPlaces().get(0).equals(article.getVectorOfCharacteristics().getLabel())).count());

            clas.setN((int) docs.stream()
                    .filter(article -> !(article.getPlaces().get(0).equals(article.getVectorOfCharacteristics().getLabel()) ||
                            article.getPlaces().equals(clas.getLabel()) ||
                            article.getVectorOfCharacteristics().getLabel().equals(clas.getLabel())))
                    .count());

            clas.setFN((int) docs.stream().filter(a ->
                    (!a.getVectorOfCharacteristics().getLabel().equals(clas.getLabel()))
             ).count()); //?

            clas.setFP((int) articles.stream().filter(article ->
                    (article.getVectorOfCharacteristics().getLabel().equals(clas.getLabel()) &&
                         !article.getVectorOfCharacteristics().getLabel().equals(article.getPlaces().get(0))))
                    .count()); //?
        });
    }

    public void setN() {
        classes.forEach(clas -> {
            clas.setN((int) articles.stream()
                            .filter(article -> article.getVectorOfCharacteristics().getLabel().equals(clas.getLabel())).count());
        });
    }


}
