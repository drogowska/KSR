package com.project1;

import com.project1.model.Article;
import com.project1.modules.Classifier;
import com.project1.modules.Extractor;
import com.project1.modules.FileReader;
import com.project1.modules.KnnAlgorithm;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.util.Collections;
import java.util.List;

public class Main {

    private Extractor extractor;
    private Classifier classifier;
    private FileReader fileReader;

    public static void main(String[] args) throws ParserConfigurationException {
            List<Article> docs = FileReader.extract(Collections.singletonList("E:\\KSR\\code\\src\\main\\resources\\reut2-000.sgm"));
            docs.size();
    }

    private void initializeModules() {
    }
}
