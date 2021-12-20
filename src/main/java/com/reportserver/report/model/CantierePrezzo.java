package com.reportserver.report.model;

import javax.validation.constraints.NotNull;

public class CantierePrezzo {

    @NotNull
    public int IdCantiere;
    @NotNull
    public double Ricarico;
    @NotNull
    public String NomeCantiere;
    @NotNull
    public String Commessa;
    @NotNull
    public String StatoCantiere;
    @NotNull
    public String RagioneSociale;
}
