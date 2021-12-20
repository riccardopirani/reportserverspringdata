package com.reportserver.report.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "Ristorante")
@Entity
public class Ristorante implements Serializable {
    @Id
    @Column(name = "IdRistorante")
    private int IdRistorante;

    @Column(name = "RagioneSociale")
    private String ragionesociale;

    @Column(name = "Costo")
    private Double costo;

    @Temporal(TemporalType.DATE)
    @Column(name = "DataInserimento")
    private Date Data;

    public String getDatainserimento() {
        return Data.toString();
    }

    public int getId() {
        return IdRistorante;
    }

    public void setId(int id) {
        this.IdRistorante = id;
    }

    public String getRagionesociale() {
        return ragionesociale;
    }

    public void setRagionesociale(String ragionesociale) {
        this.ragionesociale = ragionesociale;
    }


    public double getcosto() {
        return this.costo;
    }

    public void setData(Date data) {
        Data = data;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
}