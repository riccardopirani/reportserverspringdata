package com.reportserver.report.utils;


import org.springframework.context.annotation.Description;

/**
 * Class define report parmes
 */
@Description(value = "Report Params Constants.")
public final class ReportParams {

    /**
     * Member of
     * report
     */
    public static final String IdCantiere = "IdCantiere";
    /**
     * Member of
     * report
     */
    public static final String NomeCantiere = "NomeCantiere";
    /**
     * Member of
     * report
     */
    public static final String RagioneSociale = "RagioneSociale";
    /**
     * Member of
     * report
     */
    public static final String Commessa = "Commessa";
    /**
     * Member of
     * report
     */
    public static final String PARAM_CUSTOM_DATA_SOURCE = "CUSTOM_SOURCE_DATA";

    /**
     * Private constructor - can not be instantiated.
     */
    private ReportParams() {
    }
}
