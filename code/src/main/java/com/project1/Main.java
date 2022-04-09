package com.project1;

import com.project1.classes.Class;
import com.project1.metrics.M1;
import com.project1.metrics.M2;
import com.project1.metrics.M3;
import com.project1.metrics.Metric;
import com.project1.model.Article;
import com.project1.modules.Classifier;
import com.project1.modules.Extractor;
import com.project1.modules.FileReader;
import com.project1.modules.Statistics;

import javax.xml.parsers.ParserConfigurationException;
import java.util.*;
import java.util.stream.Collectors;

import static com.project1.Constants.placesList;

public class Main {

    public static List<Class> classes = new ArrayList();
    private static List<Article> training = new ArrayList<>();
    private static final List<Article> testing = new ArrayList<>();

    private static Extractor extractor;
    private static Classifier classifier;
    private static Statistics statistics;
    private static Metric m = null;
    private static int k = 0;
    private static int splitPercent = 0;

    public static void main(String[] args) throws ParserConfigurationException {
        if (args.length != 3) {
            printParameters();
        } else {
            switch (args[2].toUpperCase()) {
                case "M1":
                    m = new M1();
                    break;
                case "M2":
                    m = new M2();
                    break;
                case "M3":
                    m = new M3();
                    break;
                default:
                    System.out.println("Podano niewłaściwą metrykę.");
                    printParameters();
                    break;
            }
            splitPercent = Integer.valueOf(args[0]);
            k = Integer.valueOf(args[1]);
        }

        initialClasses();
        initializeModules();
        loadTraining();
        training = new ArrayList<>(extractor.extract(training));
//        int splitPercent = 30;
        splitArray(splitPercent);
        classifier.classify(k, testing, m, training);
        statistics = new Statistics(testing);
        printResults();

//        M2 m2 = new M2();
//        for (int k = 2; k <= 11; k = k + 1) {
//            classifier.classify(k, testing, m2, training);
//            statistics = new Statistics(testing);
            //classes.forEach(clas -> System.out.print(clas.getLabel() + " "));
//            //System.out.println("");
//            System.out.print(statistics.ACC() + " " + statistics.getPPVc() + " " + statistics.getTPRc() + " " + statistics.getF1c() + " ");
//            classes.forEach(clas -> System.out.print(clas.getPPV() + " "));
//            classes.forEach(clas -> System.out.print(clas.getTPR() + " "));
//            classes.forEach(clas -> System.out.print(clas.getF1() + " "));
//            System.out.println();
            //System.out.println("--------------------------------------------------");
        }

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

        /*int splitPercent = 30;
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
        System.out.println("");*/
//    }

    private static void printResults() {
        String metric = "";
        if (m.getClass().equals(M1.class)) metric = "Euklidesowej";
        else if (m.getClass().equals(M2.class)) metric = "Ulicznej";
        else if (m.getClass().equals(M3.class)) metric = "Czebyszewa";
        System.out.println("Wyniki dla stałek k równej " + k
                + ", podziale zbioru " + splitPercent + ":" + String.valueOf(100-splitPercent)
                + ", wykorzystaneje metryki " + metric
                + ":\n\n"
                + "\t ACC:  " + statistics.ACC() + "\n"
                + "\t PPVc: " + statistics.getPPVc() + "\n"
                + "\t TPRc: " + statistics.getTPRc() + "\n"
                + "\t F1c:  " + statistics.getF1c() + "\n");
        classes.forEach(clas -> {
            System.out.println("Klasa: " + clas.getLabel()
                    + "\t PPV: " + clas.getPPV()
                    + "\t TPR: " + clas.getTPR()
                    + "\t F1:  " + clas.getF1()
                    + "\n");
                });
    }

    private static void printParameters() {
        System.out.println("Wymagrane parametry:  \n"
                + "\tProcent zbioru dokumentów, stanowiący zbiór treningowy (liczba całkowita [1,99])\n"
                + "\tStała k (liczba całkowita dodatnia)\n"
                + "\tMetryka:\n"
                + "\t\t M1 - metryka euklidesowa\n"
                + "\t\t M2 - metryka uliczna\n"
                + "\t\t M3 - metryka czebyszewa");
        System.exit(0);
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
        training.forEach(doc -> doc.getVectorOfCharacteristics().setLabel(doc.getPlaces().get(0)));
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
        Collections.shuffle(training);
    }
}
