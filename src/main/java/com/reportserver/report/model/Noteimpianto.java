package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "Noteimpianto")
@Entity
public class Noteimpianto {

    @Id
    @NotNull
    private int IdNoteimpianto;

    @Column(name = "Testo")
    @NotNull
    private String Testo;

    public int getIdNoteimpianto() {
        return IdNoteimpianto;
    }

    public void setIdNoteimpianto(int idNoteimpianto) {
        IdNoteimpianto = idNoteimpianto;
    }

    public String getTesto() {
        return Testo;
    }

    public void setTesto(String testo) {
        Testo = testo;
    }
}
