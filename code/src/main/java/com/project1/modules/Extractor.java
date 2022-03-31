package com.project1.modules;

import com.project1.classes.CountryClass;
import com.project1.model.Article;
import com.project1.model.Dictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Extractor {

    private List<Dictionary> dictionaries = new ArrayList<>();
    private List<Article> articles;

    public void extract(List<Article> articles) {
        this.articles = articles;
        fillDictionaries();
        firstOccurrence(1);
        firstOccurrence(0);
        numberOfFamousBuild(3);
        setPublishedHour();
        mostCommonCountry();
    }

    private void firstOccurrence(int dictNumb) {
        AtomicReference<String> res = new AtomicReference<>("");
        articles.stream().forEach(article -> {
            for(String s : dictionaries.get(dictNumb).getContent()) {
                if(article.getBody().contains(s)) {
                    res.set(s);
                    if (dictNumb == 1) article.getVectorOfCharacteristics().setF2(s);
                    else if (dictNumb == 0) article.getVectorOfCharacteristics().setK1(s);
                    break;
                }
            }
        });
    }

    private void numberOfFamousBuild(int dictNumb) {
        AtomicInteger sum = new AtomicInteger();
        articles.forEach(article -> {
            dictionaries.get(dictNumb).getContent().forEach(s -> {
                if(article.getBody().contains(s)) {
                    sum.getAndIncrement();
                }
            });
            if(dictNumb == 3) article.getVectorOfCharacteristics().setN1(sum.get());
            else if(dictNumb == 4) article.getVectorOfCharacteristics().setN2(sum.get());
            else if(dictNumb == 5) article.getVectorOfCharacteristics().setN3(sum.get());
            else if(dictNumb == 6) article.getVectorOfCharacteristics().setN5(sum.get());
            else if(dictNumb == 7) article.getVectorOfCharacteristics().setN6(sum.get());
        });
    }

    private void setPublishedHour() {
        articles.forEach(article -> {
            article.getVectorOfCharacteristics().setL1(article.getDate().substring(12));
        });
    }

    //test
    private void mostCommonCountry() {
        CountryClass classes = new CountryClass();
        articles.forEach(article -> {
            HashMap<String, Integer> countryOccur = new HashMap<>();
            classes.forEach(c -> {
                countryOccur.put(c, StringUtils.countMatches(article.getBody().toLowerCase(),c));
            });
            if (countryOccur.entrySet().iterator().next().getValue() == 0) article.getVectorOfCharacteristics().setR1("");
            else article.getVectorOfCharacteristics().setR1(countryOccur.entrySet().iterator().next().getKey());
        });
    }

    private void fillDictionaries() {
        List<String> strings = Arrays.asList("DK1", "DF2", "DP1", "DN1", "DN2", "DN3", "DN4", "DN5", "DN6", "DR1");
        for (String string : strings) {
            dictionaries.add(new Dictionary(string));
        }
    }
}
