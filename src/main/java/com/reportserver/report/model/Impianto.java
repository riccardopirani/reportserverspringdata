package com.reportserver.report.model;

import javax.validation.constraints.NotNull;

public class Impianto {
    @NotNull
    public int IdImpianto;

    public int getIdImpianto() {
        return IdImpianto;
    }

    /**
     * Set id impianto
     *
     * @param idImpianto
     */
    public void setIdImpianto(int idImpianto) {
        IdImpianto = idImpianto;
    }
}
