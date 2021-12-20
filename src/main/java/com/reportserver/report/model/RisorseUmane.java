package com.reportserver.report.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "RisorseUmane")
@Entity
public class RisorseUmane {

    @Id
    @Column(name = "IdRisorseUmane", nullable = false)
    private Integer idrisorseumane;

    @Temporal(TemporalType.DATE)
    @Column(name = "Data")
    private Date Data;

    @Temporal(TemporalType.TIME)
    @Column(name = "OreInizio")
    private Date oreinizio;

    @Temporal(TemporalType.TIME)
    @Column(name = "OreFine")
    private Date orefine;

    @Column(name = "Descrizione")
    private String descrizione;

    @Column(name = "Pausa")
    private Double pausa;

    @Column(name = "IdUtente")
    private Integer idutente;


    public Integer getIdutente() {
        return idutente;
    }

    public void setIdutente(Integer idutente) {
        this.idutente = idutente;
    }

    public Double getPausa() {
        return pausa;
    }

    public void setPausa(Double pausa) {
        this.pausa = pausa;
    }

    public Date getOrefine() {
        return orefine;
    }

    public void setOrefine(Date orefine) {
        this.orefine = orefine;
    }


    public Date getOreinizio() {
        return oreinizio;
    }

    public void setOreinizio(Date oreinizio) {
        this.oreinizio = oreinizio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }

    public Integer getIdrisorseumane() {
        return idrisorseumane;
    }

    public void setIdrisorseumane(Integer idrisorseumane) {
        this.idrisorseumane = idrisorseumane;
    }
}