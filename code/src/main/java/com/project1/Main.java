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
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.project1.Constants.*;

public class Main {

    private static Extractor extractor;
    private static Classifier classifier;
    private static FileReader fileReader;
    public static List<Class> classes = new ArrayList();

    public static void main(String[] args) throws ParserConfigurationException {
        initialClasses();
        List<Article> docs = FileReader.extract(Collections.singletonList("E:\\KSR\\code\\src\\main\\resources\\articles\\reut2-006.sgm"));
        extractor = new Extractor();
        extractor.extract(docs);
        double d = M4.countSimilarity("summary", "summarizationnt");
        classifier = new Classifier();
//        classifier.classify(5,docs, M2, docs.get(0));
   }

    private void initializeModules() {
    }

    private static void initialClasses() {
        for (String place : placesList) {
            classes.add(new Class(place));
        }
    }
}
