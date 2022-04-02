package com.project1.modules;

import com.project1.model.Article;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.project1.Constants.*;
import static com.project1.Main.classes;

public class Extractor {

    private List<Article> articles;

    public void extract(List<Article> articles) {
        this.articles = articles;
        filterArticles();
        firstOccurrence(1);
        firstOccurrence(0);
        numberOfFamousBuild(3);
        setPublishedHour();
        mostCommonCountry();
    }

    private void filterArticles() {
        articles = articles.stream().filter(doc -> doc.getPlaces().size() == 1 && placesList.contains(doc.getPlaces().get(0)))
                .collect(Collectors.toList());
    }

    //todo - zapis panstwa nie miasta/waluty
    //k1, f2 // 0, 1
    private void firstOccurrence(int dictNumb) {
        AtomicReference<String> res = new AtomicReference<>("");
        articles.stream().forEach(article -> {
            classes.forEach(clas -> {
                for(String s : clas.getDic(dictNumb).getContent()) {
                    if(article.getBody().contains(s)) {
                        res.set(s);
                        article.getVectorOfCharacteristics().setFeatures(dictNumb, clas.getLabel());
                        break;
                    }
                }
            });
        });
    }

    //n1-6
    private void numberOfFamousBuild(int dictNumb) {
        AtomicInteger sum = new AtomicInteger();
        articles.forEach(article -> {
                classes.forEach(clas -> {
                    clas.getDic(dictNumb).getContent().forEach(s -> {
                        if (article.getBody().contains(s))
                            sum.getAndIncrement();
        //                sum += StringUtils.countMatches(article.getBody().toLowerCase(), s);
                    });
                    article.getVectorOfCharacteristics().setFeatures(dictNumb, sum.get());
            });
        });
    }

    //l1
    private void setPublishedHour() {
        articles.forEach(article -> {
            article.getVectorOfCharacteristics().setFeatures(8, article.getDate().substring(12));
        });
    }
    //p1
    //todo podzial na karaje
    private void politicianParty() {

    }

    //r1
    private void mostCommonCountry() {
        articles.forEach(article -> {
            HashMap<String, Integer> countryOccur = new HashMap<>();
            classes.forEach(c -> countryOccur.put(c.getLabel(),0));
            classes.forEach(c -> {
                c.getDR1().getContent().forEach(s -> { //?
                    countryOccur.put(c.getLabel(), StringUtils.countMatches(article.getBody().toLowerCase(), s));
                });
//                countryOccur.put(c.getLabel(), StringUtils.countMatches(article.getBody().toLowerCase(), c.getDic(9)));
            });
            Map.Entry<String, Integer> entry =  Collections.max(countryOccur.entrySet(), new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            if (entry.getValue() != 0)
                article.getVectorOfCharacteristics().setFeatures(9, entry.getKey());
        });
    }

}
