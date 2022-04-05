package com.project1.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dictionary {

    private List<String> content = new ArrayList<>();
    private final String file = "E:\\KSR\\code\\src\\main\\resources\\dic.txt";

    public Dictionary(String dictType, String prefix) {
        fill(dictType, prefix);
    }

    private void fill(String type, String prefix) {
        String dic;
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file)) {
            BufferedReader bf = new BufferedReader(new FileReader(Paths.get(file).toAbsolutePath().toFile()));
            String line;
            while ((line = bf.readLine()) != null) {
                if (line.contains(type + "_" + prefix)) {
                    line = line.toLowerCase();
                    dic = line.substring(10, line.length() - 1);
                    content = new ArrayList<>(Arrays.asList(dic.split(", ")));
                    break;
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getContent() {
        return content;
    }

}
