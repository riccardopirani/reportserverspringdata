package com.reportserver.report.model;

import org.springframework.context.annotation.Description;

import java.io.Serializable;


@Description(value = "Table Cantiere Report Model")
public class TableReport implements Serializable {

    public String Tipo;
    public String data;
    public String gg;
    public String articolo;
    public String descrizione;
    public String quantita;
    public String prezzo;
    public String nr;
    public String totale;


    public TableReport(String NR, String Tipo, String data, String GG, String Articolo, String Prezzo, String Descrizione, String Totale, String Quantita) {
        this.Tipo = Tipo;
        this.data = data;
        this.gg = GG;
        this.articolo = Articolo;
        this.descrizione = Descrizione;
        this.quantita = Quantita;
        this.prezzo = Prezzo;
        this.totale = Totale;
        this.nr = NR;

    }

    public String getTotale() {
        return totale;
    }

    public void setTotale(String totale) {
        this.totale = totale;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String data) {
        this.gg = gg;
    }

    public String getArticolo() {
        return articolo;
    }

    public void setArticolo(String articolo) {
        this.articolo = articolo;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }
}
