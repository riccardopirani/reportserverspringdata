package com.reportserver.report.model;

import org.springframework.context.annotation.Description;

import java.io.Serializable;

@Description(value = "Table Preventivo Report Model")
public class TableReportCapitoloPreventivo implements Serializable {
    public String Descrizione;
    public String Desc2;
    public Integer NumeroCapitolo;
    public String SottoCapitolo1;
    public String SottoCapitolo2;
    public String SottoCapitolo3;
    public String DescrizioneDis;
    public Double CostoUnitario;
    public Double Quantita;
    public Double TotaleCap;
    public String DescrizioneEstesa;
    public String Tipologia;
    public String UM;
    public double PrezzoRiservato;
    public byte[] Imgaes;

    public TableReportCapitoloPreventivo(String Descrizione, String Desc2, int NumeroCapitolo, String SottoCapitolo1, String SottoCapitolo2, String SottoCapitolo3, String DescrizioneDis, double CostoUnitario, double Quantita, double TotaleCap, String DescrizioneEstesa, String tipologia, String UM, double PrezzoRiservato, byte[] Images, String Tipologia) {
        this.Descrizione = Descrizione;
        this.Desc2 = Desc2;
        this.NumeroCapitolo = NumeroCapitolo;
        this.SottoCapitolo1 = SottoCapitolo1;
        this.SottoCapitolo2 = SottoCapitolo2;
        this.SottoCapitolo3 = SottoCapitolo3;
        this.DescrizioneDis = DescrizioneDis;
        this.CostoUnitario = CostoUnitario;
        this.Quantita = Quantita;
        this.TotaleCap = TotaleCap;
        this.DescrizioneEstesa = DescrizioneEstesa;
        this.Tipologia = Tipologia;
        this.UM = UM;
        this.PrezzoRiservato = PrezzoRiservato;
        this.Imgaes = Images;

    }
}
