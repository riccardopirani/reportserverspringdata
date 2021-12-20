package com.reportserver.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Cliente")
@Entity
public class Cliente {


    @Id
    @Column(name = "IdCliente")
    private int IdCliente;

    @Column(name = "RimborsoKM")
    private double RimborsoKM;

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public double getRimborsoKM() {
        return RimborsoKM;
    }

    public void setRimborsoKM(double rimborsoKM) {
        RimborsoKM = rimborsoKM;
    }

}