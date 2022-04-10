package com.project1.modules;

import com.project1.model.Article;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.project1.Main.classes;

public class Extractor {

    private List<Article> articles;

    public List<Article> extract(List<Article> articles) {
        this.articles = new ArrayList<>(articles);
        firstOccurrence(1);
        firstOccurrence(0);
        numberOfFamousBuild();
        setPublishedHour();
        mostCommonCountry(2);
        mostCommonCountry(9);
        return this.articles;
    }


    //k1, f2 // 0, 1
    private void firstOccurrence(int dictNumb) {
        AtomicBoolean flag = new AtomicBoolean(true);
        articles.stream().forEach(article -> {
            article.getBody().forEach(w -> {
                if (flag.get()) {
                    classes.forEach(clas -> {
                        for (String s : clas.getDic(dictNumb).getContent()) {
                            if (w.toLowerCase().equals(s.toLowerCase())) {
                                article.getVectorOfCharacteristics().setFeatures(dictNumb, clas.getLabel());
                                flag.set(false);
                                return;
                            }
                        }
                        if(!flag[0]) return;
                    });
                }
            });
        });
    }

    //n1-6
    private void numberOfFamousBuild() {
        final int[] sum = {0};
        articles.forEach(article -> {
            for (int i = 0; i < classes.size(); i++) {
                classes.get(i).getDN().getContent().forEach(s -> {
                    sum[0] += StringUtils.countMatches(StringUtils.join(article.getBody(), " "), s);
                });
                article.getVectorOfCharacteristics().setFeatures(i + 3, sum[0]);
                sum[0] = 0;
            }
        });
    }

    //l1
    private void setPublishedHour() {
        articles.forEach(article -> {
            article.getVectorOfCharacteristics().setFeatures(10, article.getDate().substring(12));
        });
    }

    //r1, p1 (9,2)
    private void mostCommonCountry(int party) {
        articles.forEach(article -> {
            HashMap<String, Integer> countryOccur = new HashMap<>();
            classes.forEach(c -> countryOccur.put(c.getLabel(), 0));
            classes.forEach(c -> {
                c.getDic(party).getContent().forEach(s -> {
                    countryOccur.put(c.getLabel(), countryOccur.get(c.getLabel()) +
                            StringUtils.countMatches(" " + StringUtils.join(article.getBody(), " "), " " + s + " "));
                });
            });
            Map.Entry<String, Integer> entry = Collections.max(countryOccur.entrySet(), new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            if (entry.getValue() != 0)
                article.getVectorOfCharacteristics().setFeatures(party, entry.getKey());
        });
    }

}
