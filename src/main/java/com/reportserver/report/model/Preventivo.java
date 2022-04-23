package com.reportserver.report.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Preventivo: rappresenta il costo del preventivo da imputare ad un cliente
 */
@Table(name = "Preventivo")
@Entity
public class Preventivo implements Serializable {

    @Id
    @Column(name = "IdPreventivo", nullable = false)
    private Integer idPreventivo;

    @Column(name = "IdCliente", nullable = false)
    private Integer idCliente;

    @Column(name = "VostroRiferimento")
    private String vostroRiferimento;

    @Column(name = "RiferimentoInterno")
    private String riferimentoInterno;

    @Temporal(TemporalType.DATE)
    @Column(name = "DataInserimento")
    private Date dataInserimento;

    @Column(name = "Stato")
    private String stato;

    @Column(name = "ModalitaCalcoloPercentuale")
    private String modalitaCalcoloPercentuale;

    @Column(name = "PrezzoRiservato")
    private double prezzoRiservato;

    @Column(name = "PercentualeSpeseGenerali")
    private double percentualeSpeseGenerali;

    @Column(name = "PercentualeUtile")
    private double percentualeUtile;

    @Column(name = "PercentualeMargine")
    private double percentualeMargine;

    @Column(name = "Testata")
    private String testata;

    @Column(name = "Chiusura")
    private String chiusura;

    @Column(name = "Oggetto")
    private String oggetto;

    public Preventivo() {
    }

    public Preventivo(int idPreventivo) {
        this.idPreventivo = idPreventivo;
    }


    public Integer getIdPreventivo() {
        return idPreventivo;
    }

    public void setIdPreventivo(Integer idPreventivo) {
        this.idPreventivo = idPreventivo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getRiferimentoInterno() {
        return riferimentoInterno;
    }

    public void setRiferimentoInterno(String riferimentoInterno) {
        this.riferimentoInterno = riferimentoInterno;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getModalitaCalcoloPercentuale() {
        return modalitaCalcoloPercentuale;
    }

    public void setModalitaCalcoloPercentuale(String modalitaCalcoloPercentuale) {
        this.modalitaCalcoloPercentuale = modalitaCalcoloPercentuale;
    }

    public double getPrezzoRiservato() {
        return prezzoRiservato;
    }

    public void setPrezzoRiservato(double prezzoRiservato) {
        this.prezzoRiservato = prezzoRiservato;
    }

    public double getPercentualeSpeseGenerali() {
        return percentualeSpeseGenerali;
    }

    public void setPercentualeSpeseGenerali(double percentualeSpeseGenerali) {
        this.percentualeSpeseGenerali = percentualeSpeseGenerali;
    }

    public double getPercentualeUtile() {
        return percentualeUtile;
    }

    public void setPercentualeUtile(double percentualeUtile) {
        this.percentualeUtile = percentualeUtile;
    }

    public double getPercentualeMargine() {
        return percentualeMargine;
    }

    public void setPercentualeMargine(double percentualeMargine) {
        this.percentualeMargine = percentualeMargine;
    }

    public String getTestata() {
        return testata;
    }

    public void setTestata(String testata) {
        this.testata = testata;
    }

    public String getChiusura() {
        return chiusura;
    }

    public void setChiusura(String chiusura) {
        this.chiusura = chiusura;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getVostroRiferimento() {
        return vostroRiferimento;
    }

    public void setVostroRiferimento(String vostroRiferimento) {
        this.vostroRiferimento = vostroRiferimento;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }
}
