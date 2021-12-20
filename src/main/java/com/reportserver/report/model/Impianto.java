package com.reportserver.report.model;

import javax.validation.constraints.NotNull;

public class Impianto {
    @NotNull
    public int IdImpianto;

    public int getIdImpianto() {
        return IdImpianto;
    }

    public void setIdImpianto(int idImpianto) {
        IdImpianto = idImpianto;
    }
}
