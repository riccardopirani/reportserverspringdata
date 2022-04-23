package com.reportserver.report.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Classe di Support in Java
 */
public class Support {

    public static String tempPath = "/var/tmp";

    /**
     * Arrotondamento del valori a 2 decimali
     *
     * @param value
     * @return
     */
    public static double rounddouble2decimal(double value) {
        return Double.valueOf(new DecimalFormat("#.##").format(value));
    }

    /**
     * Funzione che si occupa di applicare il ricarico percentuale
     *
     * @param percentuale
     * @param valore
     * @return
     */
    public static double rechargepercentage(double percentuale, double valore) {
        return ((percentuale * valore) / 100) + valore;
    }

    /**
     * Funzione che si occupa di eliminare un file da una cartella temporanea
     *
     * @param file
     * @return
     */
    public static Boolean deleteLocalFile(MultipartFile file) {
        try {
            File myObj = new File(Support.tempPath + "/" + file.getOriginalFilename());
            if (myObj.exists() && !myObj.isDirectory()) {
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                    return true;
                } else {
                    System.out.println("Failed to delete the file.");
                    return false;
                }
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Conversione di una giornata da inglese ad italiano esempio Monday->Lunedi
     *
     * @param now
     * @return
     */
    public static String convertDataToString(String now) {
        try {
            LocalDate theDate = LocalDate.parse(now);
            String giorno = theDate.getDayOfWeek().toString();
            if (giorno.equals("THURSDAY")) {
                return "giovedi";
            }
            if (giorno.equals("MONDAY")) {
                return "lunedi";
            }
            if (giorno.equals("WEDNESDAY")) {
                return "mercoledi";
            }
            if (giorno.equals("TUESDAY")) {
                return "martedi";
            }
            if (giorno.equals("FRIDAY")) {
                return "venerdi";
            }
            if (giorno.equals("SATURDAY")) {
                return "sabato";
            }
            if (giorno.equals("SUNDAY")) {
                return "domenica";
            }
            return "";
        } catch (Exception ex) {
            System.out.println("Errore: " + ex);
            return "";
        }
    }
}
