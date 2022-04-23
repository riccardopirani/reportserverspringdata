package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "Produttore")
@Entity
public class Produttore implements Serializable {
    @Id
    @Column(name = "IdProduttore", nullable = false)
    private Integer idProduttore;

    @Column(name = "IdFornitore")
    private Integer idFornitore;

    @Column(name = "CodMarca")
    private String CodMarca;

    @Column(name = "FamigliaDiSconto")
    private String FamigliaDiSconto;

    @Column(name = "Sconto1")
    private double Sconto1;

    @Column(name = "Sconto2")
    private double Sconto2;

    @Column(name = "Sconto3")
    private double Sconto3;

    @Column(name = "IdUtente")
    private int IdUtente;

    public String getFamigliaDiSconto() {
        return FamigliaDiSconto;
    }

    public void setFamigliaDiSconto(String famigliaDiSconto) {
        FamigliaDiSconto = famigliaDiSconto;
    }

    public double getSconto1() {
        return Sconto1;
    }

    public void setSconto1(double sconto1) {
        Sconto1 = sconto1;
    }

    public double getSconto2() {
        return Sconto2;
    }

    public void setSconto2(double sconto2) {
        Sconto2 = sconto2;
    }

    public double getSconto3() {
        return Sconto3;
    }

    public void setSconto3(double sconto3) {
        Sconto3 = sconto3;
    }

    public int getIdUtente() {
        return IdUtente;
    }

    public void setIdUtente(int idUtente) {
        IdUtente = idUtente;
    }


    public Integer getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(Integer idProduttore) {
        this.idProduttore = idProduttore;
    }

    public Integer getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(Integer idFornitore) {
        this.idFornitore = idFornitore;
    }

    public String getCodMarca() {
        return CodMarca;
    }

    public void setCodMarca(String codMarca) {
        CodMarca = codMarca;
    }
}
