package com.reportserver.report.model;

import org.springframework.context.annotation.Description;

import java.io.Serializable;

@Description(value = "Table Report Preventivo")
public class TableReportPreventivo implements Serializable {
    public String Data;
    public String NRPreventivo;
    public String NSRif;
    public String VSRif;
    public String Titolo;
    public String RagioneSociale;
    public String Indirizzo;
    public String Citta;
    public String Oggetto;
    public String Testata;
    public String LogoBase64;

    public TableReportPreventivo(String Data, String NRPreventivo, String NSRif, String VSRif, String Titolo, String RagioneSociale, String Indirizzo, String Citta, String Oggetto, String Testata, String LogoBase64) {
        this.Data = Data;
        this.NRPreventivo = NRPreventivo;
        this.Testata = Testata;
        this.Oggetto = Oggetto;
        this.NSRif = NSRif;
        this.VSRif = VSRif;
        this.Titolo = Titolo;
        this.RagioneSociale = RagioneSociale;
        this.Indirizzo = Indirizzo;
        this.Citta = Citta;
        this.LogoBase64 = LogoBase64;
    }
}
