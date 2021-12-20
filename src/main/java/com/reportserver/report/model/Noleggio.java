package com.reportserver.report.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Table(name = "Noleggio")
@Entity
public class Noleggio implements Serializable {

    @Id
    @NotNull
    private int IdNoleggio;

    @Temporal(TemporalType.DATE)
    @Column(name = "DataInizioNoleggio")
    private Date datainizionoleggio;

    @Temporal(TemporalType.DATE)
    @Column(name = "DataTermineNoleggio")
    private Date dataterminenoleggio;

    @Column(name = "TipoMezzo")
    @NotNull
    private String tipomezzo;

    @Column(name = "CostoNoleggio")
    @NotNull
    private double costonoleggio;

    public Date getDatainizionoleggio() {
        return datainizionoleggio;
    }

    public void setDatainizionoleggio(Date datainizionoleggio) {
        this.datainizionoleggio = datainizionoleggio;
    }

    public Date getDataterminenoleggio() {
        return dataterminenoleggio;
    }

    public void setDataterminenoleggio(Date dataterminenoleggio) {
        this.dataterminenoleggio = dataterminenoleggio;
    }

    public String getTipomezzo() {
        return tipomezzo;
    }

    public void setTipomezzo(String tipomezzo) {
        this.tipomezzo = tipomezzo;
    }

    public double getCostonoleggio() {
        return costonoleggio;
    }

    public void setCostonoleggio(double costonoleggio) {
        this.costonoleggio = costonoleggio;
    }
}