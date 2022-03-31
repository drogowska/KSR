package com.project1.modules;

import com.project1.model.Article;
import com.project1.model.Dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Extractor {

    private List<Dictionary> dictionaries = new ArrayList<>();
    private List<Article> articles;

    public void extract(List<Article> articles) {
        this.articles = articles;
        fillDictionaries();
        firstOccurrence(1);
        firstOccurrence(0);
        System.out.println("");
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

    private void fillDictionaries() {
        List<String> strings = Arrays.asList("DK1", "DF2", "DP1", "DN1", "DN2", "DN3", "DN4", "DN5", "DN6", "DR1");
        for (String string : strings) {
            dictionaries.add(new Dictionary(string));
        }
    }
}
