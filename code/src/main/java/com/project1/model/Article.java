package com.project1.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Article {
    private final int id;
    private final String date;
    private final List<String> topic;
    private final List<String> places;
    private final String title;
    //    private String dateline;
    private final List<String> body;

    private VectorOfCharacteristics vectorOfCharacteristics;

    public Article(int id, String date, List<String> topic, List<String> places, String title, String body) {
        this.id = id;
        this.date = date;
        this.topic = topic;
        this.places = places;
        this.title = title;
//        this.dateline = dateline;
        this.body = Arrays.stream(body.toLowerCase().replaceAll("\\p{Punct}", "").split(" ")).collect(Collectors.toList());
        vectorOfCharacteristics = new VectorOfCharacteristics();
    }

    public List<String> getBody() {
        return body;
    }

    public VectorOfCharacteristics getVectorOfCharacteristics() {
        return vectorOfCharacteristics;
    }

    public void setVectorOfCharacteristics(VectorOfCharacteristics vectorOfCharacteristics) {
        this.vectorOfCharacteristics = vectorOfCharacteristics;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public List<String> getTopic() {
        return topic;
    }

    public List<String> getPlaces() {
        return places;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("About: ").append(places.get(0)).append(", Vector[").append(vectorOfCharacteristics.toString()).append("]");
        return stringBuilder.toString();
    }

}
