package com.project1.modules;

import com.project1.classes.Class;
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

    public double getACC() {
        int p = (int) articles.stream().filter(article -> article.getPlaces().get(0).equals(article.getVectorOfCharacteristics().getLabel())).count();
        return (double) p / articles.size();
    }

    private void count() {
        /*
          P_x -> jest x, zaklasyfikowano do x

          N_x -> jest y, zaklasyfikowano do y

          FP_x -> jest y, zaklasyfikowano do x

          FN_x -> jest x, zaklasyfikowano do y
        */
        classes.forEach(clas -> {
            List<Article> zaklasyfikowaneDoClas = articles.stream().filter(article -> article.getVectorOfCharacteristics().getLabel().equals(clas.getLabel())).collect(Collectors.toList());
            List<Article> nieZaklasyfikowaneDoClas = articles.stream().filter(article -> !article.getVectorOfCharacteristics().getLabel().equals(clas.getLabel())).collect(Collectors.toList());

            clas.setP((int) zaklasyfikowaneDoClas.stream().filter(article ->
                    article.getPlaces().get(0).equals(article.getVectorOfCharacteristics().getLabel())).count());

            clas.setFP((int) zaklasyfikowaneDoClas.stream().filter(article ->
                    !article.getPlaces().get(0).equals(article.getVectorOfCharacteristics().getLabel())).count());

            int n = (int) nieZaklasyfikowaneDoClas.stream().filter(article ->
                    article.getPlaces().get(0).equals(clas.getLabel())
            ).count();
            clas.setFN(n);

            int f = (int) nieZaklasyfikowaneDoClas.stream().filter(article ->
                    !article.getPlaces().get(0).equals(clas.getLabel())
            ).count();
            clas.setN(f);
        });
    }

    public void setN() {
        classes.forEach(clas -> {
            clas.setCount((int) articles.stream()
                    .filter(article -> article.getPlaces().get(0).equals(clas.getLabel())).count());
        });
    }

    public double getF1c() {
        double result = 0;
        double sumOfWeights = 0;
        for (Class c : classes) {
            result = result + c.getCount() * c.getF1();
            sumOfWeights = sumOfWeights + c.getCount();
        }
        return result / sumOfWeights;
    }

    public double getTPRc() {
        double result = 0;
        double sumOfWeights = 0;
        for (Class c : classes) {
            result = result + c.getCount() * c.getTPR();
            sumOfWeights = sumOfWeights + c.getCount();
        }
        return result / sumOfWeights;
    }

    public double getPPVc() {
        double result = 0;
        double sumOfWeights = 0;
        for (Class c : classes) {
            result = result + c.getCount() * c.getPPV();
            sumOfWeights = sumOfWeights + c.getCount();
        }
        return result / sumOfWeights;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("ACC: ").append(String.format("%.4f", getACC()))
                .append(" PPVc: ").append(String.format("%.4f", getPPVc()))
                .append(" TPRc: ").append(String.format("%.4f", getTPRc()))
                .append(" F1c: ").append(String.format("%.4f", getF1c()));
        return result.toString();
    }
}
