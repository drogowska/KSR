package com.project1.model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dictionary {

    private List<String> content = new ArrayList<>();
    private String file = "E:\\KSR\\code\\src\\main\\resources\\dictionaries.txt";

    public Dictionary(String dictType) {
        fill(dictType);
    }

    private void fill(String type) {
        String dic;
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file)) {
            int line = getDictLine(type);
            dic = Files.lines(Paths.get(file)).skip(line).findFirst().get();
            dic = dic.substring(7, dic.length()-1);
            content = new ArrayList<>(Arrays.asList(dic.split(", ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getDictLine(String type) {
        int lineNumb = -1;
        switch(type){
            case "DK1" : {
                lineNumb = 0;
                break;
            } case "DF2" : {
                lineNumb = 1;
                break;
            } case "DP1" : {
                lineNumb = 2;
                break;
            } case "DN1" : {
                lineNumb = 3;
                break;
            } case "DN2" : {
                lineNumb = 4;
                break;
            } case "DN3" : {
                lineNumb = 5;
                break;
            } case "DN4" : {
                lineNumb = 6;
                break;
            } case "DN5" : {
                lineNumb = 7;
                break;
            } case "DN6" : {
                lineNumb = 8;
                break;
            } case "DR1" : {
                lineNumb = 9;
                break;
            }
        }
        return lineNumb;
    }

    public List<String> getContent() {
        return content;
    }

}
