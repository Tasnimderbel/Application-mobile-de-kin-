package com.example.fiinal;

public class DonneesClass {
    private String dataId;
    private String dataNom;
    private String dataDate;
    private String dataSexe;
    private String dataTel;
    private String dataAdre;
    private String dataServ;

    public String getDataId() {

        return dataId;
    }

    public String getDataNom() {

        return dataNom;
    }

    public String getDataDate() {

        return dataDate;
    }

    public String getDataSexe() {

        return dataSexe;
    }

    public String getDataTel() {

        return dataTel;
    }

    public String getDataAdre() {

        return dataAdre;
    }

    public String getDataServ() {

        return dataServ;
    }

    public DonneesClass(String dataId, String dataNom, String dataDate, String dataSexe, String dataTel, String dataAdre, String dataServ) {
        this.dataId = dataId;
        this.dataNom = dataNom;
        this.dataDate = dataDate;
        this.dataSexe = dataSexe;
        this.dataTel = dataTel;
        this.dataAdre = dataAdre;
        this.dataServ = dataServ;
    }

    public DonneesClass(){

    }
}
