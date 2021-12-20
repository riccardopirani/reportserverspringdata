package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Rapporto")
@Entity
public class Rapporto {


    @Column(name = "Immagine", length = 5000)
    private String immagine;

    @Id
    @Column(name = "idrapporto")
    private Integer idrapporto;

    @Column(name = "Visibilita")
    private Integer visibilita;

    public Integer getVisibilita() {
        return visibilita;
    }

    public void setVisibilita(Integer visibilita) {
        this.visibilita = visibilita;
    }

    public Integer getIdrapporto() {
        return idrapporto;
    }

    public void setIdrapporto(Integer idrapporto) {
        this.idrapporto = idrapporto;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }


}