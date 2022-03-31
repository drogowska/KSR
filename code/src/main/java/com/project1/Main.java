package com.project1;

import com.project1.model.Article;
import com.project1.model.Dictionary;
import com.project1.modules.Classifier;
import com.project1.modules.Extractor;
import com.project1.modules.FileReader;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Collections;
import java.util.List;

public class Main {

    private static Extractor extractor;
    private Classifier classifier;
    private FileReader fileReader;

    public static void main(String[] args) throws ParserConfigurationException {
        List<Article> docs = FileReader.extract(Collections.singletonList("E:\\KSR\\code\\src\\main\\resources\\articles\\reut2-000.sgm"));
        extractor = new Extractor();
        extractor.extract(docs);
   }

    private void initializeModules() {
    }
}
