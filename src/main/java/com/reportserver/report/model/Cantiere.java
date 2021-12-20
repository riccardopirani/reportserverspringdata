package com.reportserver.report.model;

import org.springframework.context.annotation.Description;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Description(value = "Report Model Definition")
public class Cantiere implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    @NotNull
    public int IdCantiere;
    @NotNull
    public String NomeCantiere;
    @NotNull
    public String Commessa;
    @NotNull
    public String StatoCantiere;
    @NotNull
    public String RagioneSociale;

    public Integer IdCliente;

    public Integer getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Integer idCliente) {
        IdCliente = idCliente;
    }

    public String getStatoCantiere() {
        return StatoCantiere;
    }

    public void setStatoCantiere(String statoCantiere) {
        StatoCantiere = statoCantiere;
    }

    public String getCommessa() {
        return Commessa;
    }

    public void setCommessa(String commessa) {
        Commessa = commessa;
    }

    public String getRagioneSociale() {
        return RagioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        RagioneSociale = ragioneSociale;
    }

    public int getIdCantiere() {
        return IdCantiere;
    }

    public void setIdCantiere(int IdCantiere) {
        this.IdCantiere = IdCantiere;
    }

    public String getNomeCantiere() {
        return NomeCantiere;
    }

    public void setNomeCantiere(String nomeCantiere) {
        NomeCantiere = nomeCantiere;
    }

    public void setLastName(String NomeCantiere) {
        this.NomeCantiere = NomeCantiere;
    }

    @Override
    public String toString() {
        return "Report{" +
                "IdCantiere='" + IdCantiere + '\'' +
                ", NomeCantiere='" + NomeCantiere + '\'' +
                ", Commessa='" + Commessa + '\'' + ", StatoCantiere='" + StatoCantiere + '\'' + ", RagioneSociale='" + RagioneSociale + '\'' +
                '}';
    }
}
