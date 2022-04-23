package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class CapitoloPreventivo implements Serializable {

    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "CodArt")
    private String codArt;

    @Column(name = "Descrizione")
    private String descrizione;

    @Column(name = "UM")
    private String um;

    @Column(name = "Prezzo")
    private double prezzo;

    @Column(name = "Quantita")
    private double quantita;

    @Column(name = "Totale")
    private double totale;

    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }
}
