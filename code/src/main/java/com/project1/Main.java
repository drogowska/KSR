package com.project1;

import com.project1.classes.Class;
import com.project1.metrics.M2;
import com.project1.model.Article;
import com.project1.modules.Classifier;
import com.project1.modules.Extractor;
import com.project1.modules.FileReader;
import com.project1.modules.Statistics;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.project1.Constants.placesList;

public class Main {

    public static List<Class> classes = new ArrayList();
    private static List<Article> training = new ArrayList<>();
    private static final List<Article> testing = new ArrayList<>();

    private static Extractor extractor;
    private static Classifier classifier;
    private static Statistics statistics;

    public static void main(String[] args) throws ParserConfigurationException {
        initialClasses();
        initializeModules();
        loadTraining();
        training = new ArrayList<>(extractor.extract(training));
        int splitPercent = 30;
        splitArray(splitPercent);
        setDefaultLabel();

        M2 m2 = new M2();
        for (int k = 2; k <= 11; k = k + 1) {
            classifier.classify(k, testing, m2, training);
            statistics = new Statistics(testing);
            System.out.println(statistics.toString());
            classes.forEach(clas -> System.out.println(clas.getLabel() + " PPV: " + String.format("%.4f", clas.getPPV()) + " TPR: " + String.format("%.4f", clas.getTPR())));
            System.out.println();
        }
    }

    private static void setDefaultLabel() {
        training.forEach(doc -> doc.getVectorOfCharacteristics().setLabel(doc.getPlaces().get(0)));
    }

    private static void initializeModules() {
        extractor = new Extractor();
        classifier = new Classifier();
    }

    private static void filterArticles() {
        training = training.stream().filter(doc -> doc.getPlaces().size() == 1 && placesList.contains(doc.getPlaces().get(0)))
                .collect(Collectors.toList());
    }

    private static void initialClasses() {
        for (String place : placesList) {
            classes.add(new Class(place));
        }
    }

    private static void splitArray(int splitPercent) {
        testing.clear();
        int tmp = (training.size() * (100 - splitPercent)) / 100;
        for (int i = 0; i < tmp; i++) {
            int numb = new Random().nextInt(training.size());
            testing.add(training.get(numb));
            training.remove(numb);
        }
    }

    private static void loadTraining() throws ParserConfigurationException {
        for (int i = 0; i < 22; i++) {
            String number = Integer.toString(i);
            while (number.length() != 3) {
                number = '0' + number;
            }
            training.addAll(FileReader.extract("/articles/reut2-" + number + ".sgm"));
        }
        Collections.shuffle(training);
        filterArticles();
        training = training.subList(0, 1000);
        Collections.shuffle(training);
    }


}
