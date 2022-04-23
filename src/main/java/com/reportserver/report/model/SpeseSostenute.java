package com.reportserver.report.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "SpeseSostenute")
@Entity
public class SpeseSostenute implements Serializable {
    @Id
    @Column(name = "IdSpeseSostenute")
    private int IdSpeseSostenute;

    @Column(name = "Causale")
    private String casuale;

    @Column(name = "Costo")
    private Double costo;
    @Temporal(TemporalType.DATE)
    @Column(name = "Data")
    private Date Data;

    public int getIdSpeseSostenute() {
        return IdSpeseSostenute;
    }

    public void setIdSpeseSostenute(int idSpeseSostenute) {
        IdSpeseSostenute = idSpeseSostenute;
    }

    public String getCasuale() {
        return casuale;
    }

    public void setCasuale(String casuale) {
        this.casuale = casuale;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }


}