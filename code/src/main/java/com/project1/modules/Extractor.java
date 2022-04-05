package com.project1.modules;

import com.project1.model.Article;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

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
        /*articles.forEach(article -> {
            //article.getVectorOfCharacteristics().setFeatures(0, ""); // K1
            //article.getVectorOfCharacteristics().setFeatures(1, ""); // F2
            //article.getVectorOfCharacteristics().setFeatures(2, ""); // P1
            //article.getVectorOfCharacteristics().setFeatures(3, 0); // N1
            //article.getVectorOfCharacteristics().setFeatures(4, 0); // N2
            //article.getVectorOfCharacteristics().setFeatures(5, 0); // N3
            //article.getVectorOfCharacteristics().setFeatures(6, 0); // N4
            //article.getVectorOfCharacteristics().setFeatures(7, 0); // N5
            //article.getVectorOfCharacteristics().setFeatures(8, 0); // N6
            article.getVectorOfCharacteristics().setFeatures(9, ""); // R1
            article.getVectorOfCharacteristics().setFeatures(10, ""); // L1
        });*/
        return this.articles;
    }


    //k1, f2 // 0, 1
    private void firstOccurrence(int dictNumb) {
        final boolean[] flag = {true};
        articles.stream().forEach(article -> {
            article.getBody().forEach(w -> {
                if (flag[0]) {
                    classes.forEach(clas -> {
                        for (String s : clas.getDic(dictNumb).getContent()) {
                            if (w.equals(s)) {
                                article.getVectorOfCharacteristics().setFeatures(dictNumb, clas.getLabel());
                                flag[0] = false;
                                return;
                            }
                        }
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
//        for (Article article : articles) {
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
//        }
    }

}
