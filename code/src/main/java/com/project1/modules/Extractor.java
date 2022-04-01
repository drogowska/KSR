package com.project1.modules;

import com.project1.model.Article;
import com.project1.model.Dictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.project1.Constants.dictNames;
import static com.project1.Constants.placesList;

public class Extractor {

    private List<Dictionary> dictionaries = new ArrayList<>();
    private List<Article> articles;

    public void extract(List<Article> articles) {
        this.articles = articles;
        filterArticles();
        fillDictionaries();
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


    private void mostCommonCountry() {
        List<String> classes = Arrays.asList("u.s","uk","france","west german","japan","canad");
        articles.forEach(article -> {
            HashMap<String, Integer> countryOccur = new HashMap<>();
            classes.forEach(c->countryOccur.put(c,0));
            classes.forEach(c -> {
                countryOccur.put(c, StringUtils.countMatches(article.getBody().toLowerCase(), c));
            });
            Map.Entry<String, Integer> entry =  Collections.max(countryOccur.entrySet(), new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            if (entry.getValue() != 0)
                article.getVectorOfCharacteristics().setR1(entry.getKey());
        });
    }

    private void fillDictionaries() {
        for (String string : dictNames) {
            dictionaries.add(new Dictionary(string));
        }
    }
}
