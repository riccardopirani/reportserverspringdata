package com.reportserver.report.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Table(name = "Kilometri")
@Entity
public class Kilometri implements Serializable {

    @Id
    @NotNull
    private int IdCantiere;

    @Column(name = "TipoMezzo")
    @NotNull
    private String tipomezzo;

    @Column(name = "Targa")
    @NotNull
    private String targa;

    @Column(name = "Kilometri")
    @NotNull
    private double kilometri;

    @Temporal(TemporalType.DATE)
    @Column(name = "Data")
    private Date Data;

    public String getTipomezzo() {
        return tipomezzo;
    }

    public void setTipomezzo(String tipomezzo) {
        this.tipomezzo = tipomezzo;
    }


    public int getIdCantiere() {
        return IdCantiere;
    }

    public void setIdCantiere(int idCantiere) {
        IdCantiere = idCantiere;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public double getKilometri() {
        return kilometri;
    }

    public void setKilometri(double kilometri) {
        this.kilometri = kilometri;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }
}