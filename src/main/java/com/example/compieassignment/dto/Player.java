package com.example.compieassignment.dto;

public class Player {
    private int id;
    private String first_name;
    private String last_name;
    private String position;
    private String height;
    private String weight;
    private String jersey_number;
    private String college;
    private String country;
    private int draft_year;
    private int draft_round;
    private int draft_number;

    // Constructor
    public Player(int id, String first_name, String last_name, String position, String height, String weight,
                            String jersey_number, String college, String country, int draft_year, int draft_round, int draft_number) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.jersey_number = jersey_number;
        this.college = college;
        this.country = country;
        this.draft_year = draft_year;
        this.draft_round = draft_round;
        this.draft_number = draft_number;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getJersey_number() {
        return jersey_number;
    }

    public void setJersey_number(String jersey_number) {
        this.jersey_number = jersey_number;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDraft_year() {
        return draft_year;
    }

    public void setDraft_year(int draft_year) {
        this.draft_year = draft_year;
    }

    public int getDraft_round() {
        return draft_round;
    }

    public void setDraft_round(int draft_round) {
        this.draft_round = draft_round;
    }

    public int getDraft_number() {
        return draft_number;
    }

    public void setDraft_number(int draft_number) {
        this.draft_number = draft_number;
    }
}
