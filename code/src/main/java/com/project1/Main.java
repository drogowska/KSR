package com.project1;

import com.project1.metrics.M1;
import com.project1.metrics.M2;
import com.project1.metrics.M3;
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

    public static List<Class> classes = new ArrayList();
    private static List<Article> training = new ArrayList<>();
    private static List<Article> testing = new ArrayList<>();

    private static Extractor extractor;
    private static Classifier classifier;
    private static Statistics statistics;

    public static void main(String[] args) throws ParserConfigurationException {
        initialClasses();
        extractor = new Extractor();
        loadTraining();
        training = new ArrayList<>(extractor.extract(training));
        /*int splitPercent = 30;
        splitArray(splitPercent);

        classifier = new Classifier();
        M2 m2 = new M2();
        for(int k=2; k<=11; k=k+1) {
            classifier.classify(k,testing, m2, training);
            statistics = new Statistics(testing);
            //classes.forEach(clas -> System.out.print(clas.getLabel() + " "));
            //System.out.println("");
            System.out.print(statistics.ACC() + " " +  statistics.getPPVc() + " " +  statistics.getTPRc() + " " +  statistics.getF1c() + " ");
            classes.forEach(clas -> System.out.print(clas.getPPV() + " "));
            classes.forEach(clas -> System.out.print(clas.getTPR() + " "));
            System.out.println("");
            //System.out.println("--------------------------------------------------");
        }*/

        /*List<Article> originalTraining = new ArrayList<>(training);

        for(int splitPercent=10; splitPercent<=50; splitPercent=splitPercent+10) {
            training = new ArrayList<>(originalTraining);
            splitArray(splitPercent);
            classifier = new Classifier();
            M1 m1 = new M1();
            classifier.classify(5,testing, m1, training);
            statistics = new Statistics(testing);
            //classes.forEach(clas -> System.out.print(clas.getLabel() + " "));
            //System.out.println("");
            System.out.print(statistics.ACC() + " " +  statistics.getPPVc() + " " +  statistics.getTPRc() + " " +  statistics.getF1c() + " ");
            classes.forEach(clas -> System.out.print(clas.getPPV() + " "));
            classes.forEach(clas -> System.out.print(clas.getTPR() + " "));
            System.out.println("");
            //System.out.println("--------------------------------------------------");
        }*/

        /*int splitPercent = 30;
        splitArray(splitPercent);

        classifier = new Classifier();
        M2 m2 = new M2();
        List.of(new M1(), new M2(), new M3()).forEach(metric -> {
            classifier.classify(5,testing, m2, training);
            statistics = new Statistics(testing);
            //classes.forEach(clas -> System.out.print(clas.getLabel() + " "));
            //System.out.println("");
            System.out.print(statistics.ACC() + " " +  statistics.getPPVc() + " " +  statistics.getTPRc() + " " +  statistics.getF1c() + " ");
            classes.forEach(clas -> System.out.print(clas.getPPV() + " "));
            classes.forEach(clas -> System.out.print(clas.getTPR() + " "));
            System.out.println("");
            //System.out.println("--------------------------------------------------");
        });*/

        int splitPercent = 30;
        splitArray(splitPercent);

        classifier = new Classifier();
        M2 m2 = new M2();
        classifier.classify(5,testing, m2, training);
        statistics = new Statistics(testing);
        //classes.forEach(clas -> System.out.print(clas.getLabel() + " "));
        //System.out.println("");
        System.out.print(statistics.ACC() + " " +  statistics.getPPVc() + " " +  statistics.getTPRc() + " " +  statistics.getF1c() + " ");
        classes.forEach(clas -> System.out.print(clas.getPPV() + " "));
        classes.forEach(clas -> System.out.print(clas.getTPR() + " "));
        System.out.println("");
   }

    private void initializeModules() {
    }

    private static void initialClasses() {
        for (String place : placesList) {
            classes.add(new Class(place));
        }
    }

    private static void splitArray(int splitPercent) {
        testing.clear();
        int tmp = (training.size() * (100 - splitPercent)) / 100;
        for(int i = 0; i < tmp; i++) {
            int numb = new Random().nextInt(training.size());
            testing.add(training.get(numb));
            training.remove(numb);
        }
        training.forEach(doc -> doc.getVectorOfCharacteristics().setLabel(doc.getPlaces().get(0)));
    }

    private static void loadTraining() throws ParserConfigurationException {
        training = FileReader.extract(Collections.singletonList("D:\\KSR\\code\\src\\main\\resources\\articles\\reut2-001.sgm"));
        for(int i=2; i<22; i++) {
            String number = Integer.toString(i);
            while(number.length() != 3) {
                number = '0' + number;
            }
            training.addAll(FileReader.extract(Collections.singletonList("D:\\KSR\\code\\src\\main\\resources\\articles\\reut2-" + number + ".sgm")));
        }
        Collections.shuffle(training);
        training = training.subList(0, 8000);
        Collections.shuffle(training);
    }
}
