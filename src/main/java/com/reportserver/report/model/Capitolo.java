package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Classe che rappresenta il Capitolo nel Database
 */
@Table(name = "Capitolo")
@Entity
public class Capitolo implements Serializable {

    @Id
    @Column(name = "IdCapitolo")
    private int idCapitolo;

    @Column(name = "IdPreventivo")
    private int idPreventivo;

    @Column(name = "NumeroCapitolo")
    private int numeroCapitolo;

    @Column(name = "Descrizione")
    private String descrizione;

    @Column(name = "Ricarico")
    private double ricarico;

    @Column(name = "Modificaincorso")
    private String modificaincorso;

    @Column(name = "Tipologia")
    private String tipologia;

    @Column(name = "PrezzoRiservato")
    private double prezzoRiservato;

    public int getIdCapitolo() {
        return idCapitolo;
    }

    public void setIdCapitolo(int idCapitolo) {
        this.idCapitolo = idCapitolo;
    }

    public int getIdPreventivo() {
        return idPreventivo;
    }

    public void setIdPreventivo(int idPreventivo) {
        this.idPreventivo = idPreventivo;
    }

    public int getNumeroCapitolo() {
        return numeroCapitolo;
    }

    public void setNumeroCapitolo(int numeroCapitolo) {
        this.numeroCapitolo = numeroCapitolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getRicarico() {
        return ricarico;
    }

    public void setRicarico(double ricarico) {
        this.ricarico = ricarico;
    }

    public String getModificaincorso() {
        return modificaincorso;
    }

    public void setModificaincorso(String modificaincorso) {
        this.modificaincorso = modificaincorso;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public double getPrezzoRiservato() {
        return prezzoRiservato;
    }

    public void setPrezzoRiservato(double prezzoRiservato) {
        this.prezzoRiservato = prezzoRiservato;
    }
}