package com.project1.modules;

import com.project1.model.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private static final String REUTERS = "REUTERS";
    private static final String DATE = "DATE";
    private static final String TOPICS = "TOPICS";
    private static final String PLACES = "PLACES";
    private static final String TITLE = "TITLE";
    private static final String TEXT = "TEXT";
    private static final String DATELINE = "DATELINE";
    private static final String BODY = "BODY";

    public static List<Article> extract(List<String> filesDirPath) throws ParserConfigurationException {
        List<Article> docs = new ArrayList<>();

        filesDirPath.forEach(file -> {
            List<String> sgml = null;
            try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file)) {
                try {
                    sgml = Files.readAllLines(Paths.get(file).toAbsolutePath(), StandardCharsets.UTF_8);
                } catch (MalformedInputException e) {
                    sgml = Files.readAllLines(Paths.get(file).toAbsolutePath(), Charset.forName("CP1250"));
                }

                Document doc = Jsoup.parse(String.valueOf(sgml), "", Parser.xmlParser());
                final int[] i = {0};
                doc.select(REUTERS).forEach(d -> {
                    Element textNode = d.getElementsByTag(TEXT).first();
                    String content = (d.getElementsByTag(BODY).first() == null) ? textNode.childNode(textNode.childNodeSize() - 1).toString() :
                            d.getElementsByTag(BODY).first().text();
                    String title = (textNode.getElementsByTag(TITLE).first() == null) ? "" :
                            textNode.getElementsByTag(TITLE).first().text();
                    String dateline = (d.getElementsByTag(DATELINE).first() == null) ? "" : d.getElementsByTag(DATELINE).first().text();
                    docs.add(new Article(i[0],
                            d.getElementsByTag(DATE).first().text(),
                            d.getElementsByTag(TOPICS).first().getElementsByTag("D").eachText(),
                            d.getElementsByTag(PLACES).first().getElementsByTag("D").eachText(),
                            title,
                            content));
                    i[0]++;
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return docs;
    }

    public static void writeResult(List<Article> articles) {
    }
}
