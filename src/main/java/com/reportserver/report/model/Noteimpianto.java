package com.reportserver.report.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "Noteimpianto")
@Entity
public class Noteimpianto {

    @Id
    @NotNull
    private int IdNoteimpianto;

    @Column(name = "Testo")
    @NotNull
    private String Testo;


    @Column(name = "IdUtente")
    @NotNull
    private int IdUtente;


    @Temporal(TemporalType.DATE)
    @Column(name = "DataInserimento")
    private Date datainserimento;


    public int getIdUtente() {
        return IdUtente;
    }

    public void setIdUtente(int idUtente) {
        IdUtente = idUtente;
    }

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

    public Date getDatainserimento() {
        return datainserimento;
    }

    public void setDatainserimento(Date datainserimento) {
        this.datainserimento = datainserimento;
    }

}
