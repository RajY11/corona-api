package com.r3tech.springbootcovidapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CoronaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String country;
    private String state;
    private int reportedCases;
    public CoronaData(){}

    public CoronaData(int id, String country,String state, int reportedCases) {
        Id = id;
        this.country = country;
        this.state = state;
        this.reportedCases = reportedCases;

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getReportedCases() {
        return reportedCases;
    }

    public void setReportedCases(int reportedCases) {
        this.reportedCases = reportedCases;
    }


}
