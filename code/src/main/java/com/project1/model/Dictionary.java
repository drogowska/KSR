package com.project1.model;

import com.project1.modules.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dictionary {

    private final String dictionaryFileName = "/dictionaries.txt";
    private List<String> content = new ArrayList<>();

    public Dictionary(String dictType, String prefix) {
        fill(dictType, prefix);
    }

    private void fill(String type, String prefix) {
        String dic;
        List<String> lines = FileReader.readFromResources(dictionaryFileName);
        String dicLine = lines.stream().filter(line -> line.contains(type + "_" + prefix)).collect(Collectors.toList()).get(0);
        dicLine = dicLine.toLowerCase().substring(10, dicLine.length() - 1);
        content = new ArrayList<>(Arrays.asList(dicLine.split(", ")));
    }

    public List<String> getContent() {
        return content;
    }

}
