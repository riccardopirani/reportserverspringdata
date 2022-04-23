package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Cliente")
@Entity
public class Cliente {
    @Id
    @Column(name = "IdCliente")
    private int IdCliente;

    @Column(name = "RimborsoKM")
    private double RimborsoKM;

    @Column(name = "RagioneSociale")
    private String ragioneSociale;

    @Column(name = "Titolo")
    private String titolo;

    @Column(name = "Indirizzo")
    private String indirizzo;

    @Column(name = "Provincia")
    private String provincia;

    @Column(name = "Citta")
    private String citta;

    @Column(name = "Cap")
    private String cap;

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public double getRimborsoKM() {
        return RimborsoKM;
    }

    public void setRimborsoKM(double rimborsoKM) {
        RimborsoKM = rimborsoKM;
    }

}