package com.example.b10z.covidinfo;

public class WorldStats {

    private String country, total_cases, new_cases, new_recovered, active_cases, active_seriousCritical, TotCases1Mpop, Deaths1Mpop, TotalTests, Tests1Mpop, Population, Continent, CaseeveryXppl, DeatheveryXppl, TesteveryXppl;
    private String total_recovered, total_deaths, new_deaths;
    private String[] StoredData;


    public WorldStats(String[] data) {
        this.StoredData = data;
        setNew_deaths();
        setTotal_cases();
        setNew_cases();
        setCountry();
        setActive_cases();
        setActive_seriousCritical();
        setTotCases1Mpop();
        setNew_recovered();
        setDeaths1Mpop();
        setTotalTests();
        setTests1Mpop();
        setPopulation();
        setContinent();
        setCaseeveryXppl();
        setDeatheveryXppl();
        setTesteveryXppl();
        setTotal_deaths();
        setTotal_recovered();
    }


    public void setNew_deaths() {
        this.new_deaths = StoredData[4];
    }
    public void setTotal_cases() {
        this.total_cases = StoredData[1];
    }
    public void setNew_cases() {
        this.new_cases = StoredData[2];
    }
    public void setCountry() {
        this.country = StoredData[0];
    }
    public void setActive_cases() {
        this.active_cases = StoredData[7];
    }
    public void setActive_seriousCritical() {
        this.active_seriousCritical = StoredData[8];
    }
    public void setTotCases1Mpop() {
        TotCases1Mpop = StoredData[9];
    }
    public void setNew_recovered() {
        this.new_recovered = StoredData[6];
    }
    public void setDeaths1Mpop() {
        Deaths1Mpop = StoredData[10];
    }
    public void setTotalTests() {
        TotalTests = StoredData[11];
    }
    public void setTests1Mpop() {
        Tests1Mpop = StoredData[12];
    }
    public void setPopulation() {
        Population = StoredData[13];
    }
    public void setContinent() {
        Continent = StoredData[14];
    }
    public void setCaseeveryXppl() {
        CaseeveryXppl = StoredData[15];
    }
    public void setDeatheveryXppl() {
        DeatheveryXppl = StoredData[16];
    }
    public void setTesteveryXppl() {
        TesteveryXppl = StoredData[17];
    }
    public void setTotal_deaths() {
        this.total_deaths = StoredData[3];
    }
    public void setTotal_recovered() {
        this.total_recovered = StoredData[5];
    }

    public String getCountry() {
        return country;
    }
    public String getTotal_cases() {
        return total_cases;
    }
    public String getNew_cases() {
        return new_cases;
    }
    public String getNew_recovered() {
        return new_recovered;
    }
    public String getActive_cases() {
        return active_cases;
    }
    public String getActive_seriousCritical() {
        return active_seriousCritical;
    }
    public String getTotCases1Mpop() {
        return TotCases1Mpop;
    }
    public String getDeaths1Mpop() {
        return Deaths1Mpop;
    }
    public String getTotalTests() {
        return TotalTests;
    }
    public String getTests1Mpop() {
        return Tests1Mpop;
    }
    public String getPopulation() {
        return Population;
    }
    public String getContinent() {
        return Continent;
    }
    public String getCaseeveryXppl() {
        return CaseeveryXppl;
    }
    public String getDeatheveryXppl() {
        return DeatheveryXppl;
    }
    public String getTesteveryXppl() {
        return TesteveryXppl;
    }
    public String getTotal_recovered() {
        return total_recovered;
    }
    public String getTotal_deaths() {
        return total_deaths;
    }
    public String getNew_deaths() {
        return new_deaths;
    }
    public String[] getStoredData() {
        return StoredData;
    }


}


