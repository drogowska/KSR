package com.project1.model;

import com.project1.classes.CountryClass;

import java.util.List;

public class Article {
    private int id;
    private String date;
    private List<String> topic;
    private List<String> places;
    private String title;
    private String dateline;
    private String body;

    private VectorOfCharacteristics vectorOfCharacteristics;
    private NoStringVectorOfCharacteristics noStringVectorOfCharacteristics;
    private CountryClass countryClass;

    public Article(int id, String date, List<String> topic, List<String> places, String title, String dateline, String body) {
        this.id = id;
        this.date = date;
        this.topic = topic;
        this.places = places;
        this.title = title;
        this.dateline = dateline;
        this.body = body;
    }

    public VectorOfCharacteristics getVectorOfCharacteristics() {
        return vectorOfCharacteristics;
    }

    public void setVectorOfCharacteristics(VectorOfCharacteristics vectorOfCharacteristics) {
        this.vectorOfCharacteristics = vectorOfCharacteristics;
    }

    public NoStringVectorOfCharacteristics getNoStringVectorOfCharacteristics() {
        return noStringVectorOfCharacteristics;
    }

    public void setNoStringVectorOfCharacteristics(NoStringVectorOfCharacteristics noStringVectorOfCharacteristics) {
        this.noStringVectorOfCharacteristics = noStringVectorOfCharacteristics;
    }

    public CountryClass getCountryClass() {
        return countryClass;
    }

    public void setCountryClass(CountryClass countryClass) {
        this.countryClass = countryClass;
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

    public String getDateline() {
        return dateline;
    }

    public String getBody() {
        return body;
    }
}
