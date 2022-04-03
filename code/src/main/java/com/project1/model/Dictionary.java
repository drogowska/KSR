package com.project1.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dictionary {

    private List<String> content = new ArrayList<>();
    private String file = "D:\\KSR\\code\\src\\main\\resources\\dic.txt";

    public Dictionary(String dictType, String prefix) {
        fill(dictType, prefix);
    }

    private void fill(String type, String prefix) {
        String dic;
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file)) {
            BufferedReader bf = new BufferedReader(new FileReader(Paths.get(file).toAbsolutePath().toFile()));
            String line;
            while ((line = bf.readLine()) != null) {
                if(line.contains(type + "_" + prefix)) {
                    line = line.toLowerCase();
                    dic = line.substring(10, line.length()-1);
                    content = new ArrayList<>(Arrays.asList(dic.split(", ")));
                    break;
                }
            }
            bf.close();
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
