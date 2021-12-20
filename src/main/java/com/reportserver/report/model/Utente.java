package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Utente")
@Entity
public class Utente {

    @Id
    @Column(name = "IdUtente", nullable = false)
    private Integer idutente;

    @Column(name = "Nome")
    private String Nome;

    @Column(name = "Cognome")
    private String Cognome;

    @Column(name = "CostoInterno")
    private double costointerno;

    @Column(name = "CostoFatturazione")
    private double costofatturazione;


    public Integer getIdutente() {
        return idutente;
    }

    public void setIdutente(Integer idutente) {
        this.idutente = idutente;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public double getCostointerno() {
        return costointerno;
    }

    public void setCostointerno(double costointerno) {
        this.costointerno = costointerno;
    }

    public double getCostofatturazione() {
        return costofatturazione;
    }

    public void setCostofatturazione(double costofatturazione) {
        this.costofatturazione = costofatturazione;
    }


}