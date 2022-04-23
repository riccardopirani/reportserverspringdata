package com.reportserver.report.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Classe che si occupa del mapping dell'articolo sul db
 */
@Table(name = "Articolo")
@Entity
public class Articolo implements Serializable {

    @Id
    @Column(name = "IdArticolo")
    private int idArticolo;

    @Column(name = "CodMarca")
    private String codmarca;

    @Column(name = "UM")
    private String um;

    @Column(name = "CodArt")
    private String codart;

    @Column(name = "PrezzoListino")
    private double prezzolistino;

    @Column(name = "FamigliaDiSconto")
    private String famigliadisconto;

    @Column(name = "Descrizione")
    private String descrizione;

    @Temporal(TemporalType.DATE)
    @Column(name = "DataInserimento")
    private Date Data;

    @Column(name = "Prezzo")
    private double prezzo;

    @Column(name = "CodEAN", nullable = true)
    private BigInteger codEAN;

    @Column(name = "MoltiplicatorePrezzo", nullable = true)
    private Integer moltiplicatorePrezzo;

    public Articolo() {
    }

    public Articolo(String codArt, String descrizione, double prezzo) {
        this.codart = codArt;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    public Articolo(String CodMarca, String CodArt, String Descrizione, String UM, Double Prezzo, Double PrezzoListino, String Barcode, String FamigliaDiSconto, int MoltiplicatorePrezzo, BigInteger CodEAN) {
        this.codart = CodArt;
        this.codEAN = CodEAN;
        this.codmarca = CodMarca;
        this.descrizione = Descrizione;
        this.um = UM;
        this.prezzo = Prezzo;
        this.prezzolistino = PrezzoListino;
        this.famigliadisconto = FamigliaDiSconto;
        this.moltiplicatorePrezzo = MoltiplicatorePrezzo;
    }


    public int getMoltiplicatorePrezzo() {
        return moltiplicatorePrezzo;
    }

    public void setMoltiplicatorePrezzo(int moltiplicatorePrezzo) {
        this.moltiplicatorePrezzo = moltiplicatorePrezzo;
    }

    public String getCodmarca() {
        return codmarca;
    }

    public void setCodmarca(String codmarca) {
        this.codmarca = codmarca;
    }

    public String getFamigliadisconto() {
        return famigliadisconto;
    }

    public void setFamigliadisconto(String famigliadisconto) {
        this.famigliadisconto = famigliadisconto;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public String getCodart() {
        return codart;
    }

    public void setCodart(String codart) {
        this.codart = codart;
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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public double getPrezzolistino() {
        return prezzolistino;
    }

    public void setPrezzolistino(double prezzolistino) {
        this.prezzolistino = prezzolistino;
    }

    public BigInteger getCodEAN() {
        return codEAN;
    }

    public void setCodEAN(BigInteger codEAN) {
        codEAN = codEAN;
    }
}
