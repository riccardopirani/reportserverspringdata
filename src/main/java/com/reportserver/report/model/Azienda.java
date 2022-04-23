package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "Azienda")
@Entity
public class Azienda implements Serializable {

    @Id
    @Column(name = "IdAzienda")
    private int idAzienda;

    @Column(name = "NomeAzienda", nullable = true)
    private String nomeAzienda;


    @Column(name = "Logo", nullable = true)
    private String logo;

    public int getIdAzienda() {
        return idAzienda;
    }

    public void setIdAzienda(int idAzienda) {
        this.idAzienda = idAzienda;
    }

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}