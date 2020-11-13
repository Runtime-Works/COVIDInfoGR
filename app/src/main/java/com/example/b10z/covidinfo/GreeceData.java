package com.example.b10z.covidinfo;

public class GreeceData {
    private String nameGR, total_cases, new_cases;
    private String nameEN, total_deaths, new_deaths;

    public GreeceData(String name1, String name2, String tot_cases, String active_cases, String tot_deaths, String new_d) {
        this.nameGR = name1;
        this.nameEN = name2;
        this.total_cases = tot_cases;
        this.new_cases = active_cases;
        this.total_deaths = tot_deaths;
        this.new_deaths = new_d;
    }

    public String getNameGR() {
        return nameGR;
    }

    public String getNameEN() {
        return nameEN;
    }

    public String getTotal_cases() {
        return total_cases;
    }

    public String getNew_cases() {
        return new_cases;
    }

    public String getTotal_deaths() {
        return total_deaths;
    }

    public String getNew_deaths() {
        return new_deaths;
    }

}
