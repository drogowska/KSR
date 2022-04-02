package com.project1;

import com.project1.metrics.M2;
import com.project1.metrics.M4;
import com.project1.model.Article;
import com.project1.model.Dictionary;
import com.project1.model.VectorOfCharacteristics;
import com.project1.modules.Classifier;
import com.project1.modules.Extractor;
import com.project1.modules.FileReader;
import com.project1.classes.Class;
import com.project1.modules.Statistics;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.project1.Constants.*;

public class Main {

    private static Extractor extractor;
    private static Classifier classifier;
    public static List<Class> classes = new ArrayList();
    private static List<Article> training = new ArrayList<>();
    private static List<Article> testing = new ArrayList<>();
    private static Statistics statistics;

    public static void main(String[] args) throws ParserConfigurationException {
        initialClasses();
        extractor = new Extractor();
        training = FileReader.extract(Collections.singletonList("E:\\KSR\\code\\src\\main\\resources\\articles\\reut2-000.sgm"));
        training = new ArrayList<>(extractor.extract(training));
//        training = training.stream().limit(10).collect(Collectors.toList());
        int splitPercent = 30;
        splitArray(splitPercent);

        double d = M4.countSimilarity("summary", "summarizationnt");
        classifier = new Classifier();
        M2 m2 = new M2();
        classifier.classify(5,testing, m2, training);
        statistics = new Statistics(testing);
        System.out.println(statistics.ACC());
   }

    private void initializeModules() {
    }

    private static void initialClasses() {
        for (String place : placesList) {
            classes.add(new Class(place));
        }
    }

    private static void splitArray(int splitPercent) {
        int tmp = training.size() * (100 - splitPercent) / 100;
        for(int i = 0; i < tmp; i++) {
            int numb = new Random().nextInt(training.size());
            testing.add(training.get(numb));
            training.remove(numb);
        }
        training.forEach(doc -> doc.getVectorOfCharacteristics().setLabel(doc.getPlaces().get(0)));
    }
}
