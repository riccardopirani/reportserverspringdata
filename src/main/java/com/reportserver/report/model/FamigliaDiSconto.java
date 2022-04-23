package com.reportserver.report.model;

import javax.validation.constraints.NotNull;

public class FamigliaDiSconto {
    @NotNull
    private String FamigliaDisconto;
    @NotNull
    private String CodMarcaCode;

    public String getFamigliaDisconto() {
        return FamigliaDisconto;
    }

    public void setFamigliaDisconto(String famigliaDisconto) {
        FamigliaDisconto = famigliaDisconto;
    }

    public String getCodMarcaCode() {
        return CodMarcaCode;
    }

    public void setCodMarcaCode(String codMarcaCode) {
        CodMarcaCode = codMarcaCode;
    }
}
