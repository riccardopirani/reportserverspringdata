package com.reportserver.report.utils;

import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Support {

    //Round 2 decimal
    public static double rounddouble2decimal(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        double risvalue = Double.valueOf(df.format(value));
        return risvalue;
    }

    //Ricarico percentuale
    public static double rechargepercentage(double percentuale, double valore) {
        return ((percentuale * valore) / 100) + valore;
    }

    //Generazione QRCode
    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return ImageIO.read(bis);
    }

    //Difference between hour
    public static String differencehour(String oreinizio, String orefine) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(oreinizio);
        Date date2 = format.parse(orefine);
        long difference = date2.getTime() - date1.getTime();
        int minutes = (int) ((difference / (1000 * 60)) % 60);
        int hours = (int) ((difference / (1000 * 60 * 60)) % 24);
        return "" + hours + ":" + minutes;
    }

    //Convert data to string day of week
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
