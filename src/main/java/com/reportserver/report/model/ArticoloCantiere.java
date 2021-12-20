package com.reportserver.report.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "ArticoloCantiere")
@Entity
public class ArticoloCantiere implements Serializable {

    @Id
    @Column(name = "IdArticoloCantiere")
    private int idArticoloCantiere;


    @Column(name = "CodArt")
    private String codart;


    @Column(name = "Descrizione")
    private String descrizione;

    @Temporal(TemporalType.DATE)
    @Column(name = "DataInserimento")
    private Date Data;

    @Column(name = "Quantita")
    private double quantita;


    @Column(name = "Prezzo")
    private double prezzo;

    public int getIdArticoloCantiere() {
        return idArticoloCantiere;
    }

    public void setIdArticoloCantiere(int idArticoloCantiere) {
        this.idArticoloCantiere = idArticoloCantiere;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    public String getCodart() {
        return codart;
    }

    public void setCodart(String codart) {
        this.codart = codart;
    }


    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }


    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }


}