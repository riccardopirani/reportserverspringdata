package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "SottoCapitolo")
@Entity
public class SottoCapitolo implements Serializable {

    @Id
    @Column(name = "IdSottoCapitolo", nullable = false)
    private Integer idSottoCapitolo;

    @Column(name = "IdCapitolo", nullable = false)
    private Integer idCapitolo;

    @Column(name = "Tipo")
    private String tipo;

    @Column(name = "Codice")
    private String codice;

    @Column(name = "CodiceDistinta")
    private Integer codiceDistinta;

    @Column(name = "Descrizione")
    private String descrizione;

    @Column(name = "DescrizioneEstesa")
    private String descrizioneEstesa;

    @Column(name = "UnitaMisura")
    private String unitaMisura;

    @Column(name = "SottoCapitolo1")
    private String sottoCapitolo1;

    @Column(name = "SottoCapitolo2")
    private String sottoCapitolo2;

    @Column(name = "SottoCapitolo3")
    private String sottoCapitolo3;

    @Column(name = "Note")
    private String note;

    @Column(name = "RicaricoManodopera")
    private Double ricaricoManodopera;

    @Column(name = "Quantita")
    private Double quantita;

    @Column(name = "CostoUnitario")
    private Double costoUnitario;

    @Column(name = "Images")
    private byte[] images;

    @Column(name = "PrezzoListino")
    private Double prezzolistino;

    @Column(name = "PrezzoPersonalizzato")
    private Double prezzoPersonalizzato;

    public Integer getIdSottoCapitolo() {
        return idSottoCapitolo;
    }

    public void setIdSottoCapitolo(Integer idSottoCapitolo) {
        this.idSottoCapitolo = idSottoCapitolo;
    }

    public Integer getIdCapitolo() {
        return idCapitolo;
    }

    public void setIdCapitolo(Integer idCapitolo) {
        this.idCapitolo = idCapitolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Integer getCodiceDistinta() {
        return codiceDistinta;
    }

    public void setCodiceDistinta(Integer codiceDistinta) {
        this.codiceDistinta = codiceDistinta;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizioneEstesa() {
        return descrizioneEstesa;
    }

    public void setDescrizioneEstesa(String descrizioneEstesa) {
        this.descrizioneEstesa = descrizioneEstesa;
    }

    public String getUnitaMisura() {
        return unitaMisura;
    }

    public void setUnitaMisura(String unitaMisura) {
        this.unitaMisura = unitaMisura;
    }

    public String getSottoCapitolo1() {
        return sottoCapitolo1;
    }

    public void setSottoCapitolo1(String sottoCapitolo1) {
        this.sottoCapitolo1 = sottoCapitolo1;
    }

    public String getSottoCapitolo2() {
        return sottoCapitolo2;
    }

    public void setSottoCapitolo2(String sottoCapitolo2) {
        this.sottoCapitolo2 = sottoCapitolo2;
    }

    public String getSottoCapitolo3() {
        return sottoCapitolo3;
    }

    public void setSottoCapitolo3(String sottoCapitolo3) {
        this.sottoCapitolo3 = sottoCapitolo3;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getRicaricoManodopera() {
        return ricaricoManodopera;
    }

    public void setRicaricoManodopera(Double ricaricoManodopera) {
        this.ricaricoManodopera = ricaricoManodopera;
    }

    public Double getQuantita() {
        return quantita;
    }

    public void setQuantita(Double quantita) {
        this.quantita = quantita;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public Double getPrezzolistino() {
        return prezzolistino;
    }

    public void setPrezzolistino(Double prezzolistino) {
        this.prezzolistino = prezzolistino;
    }

    public Double getPrezzoPersonalizzato() {
        return prezzoPersonalizzato;
    }

    public void setPrezzoPersonalizzato(Double prezzoPersonalizzato) {
        this.prezzoPersonalizzato = prezzoPersonalizzato;
    }
}