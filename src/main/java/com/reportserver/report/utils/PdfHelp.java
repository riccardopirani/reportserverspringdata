package com.reportserver.report.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

public class PdfHelp {
    public boolean CastBase64DecodePdf(String datafile, String base64) {
        File file = new File(datafile);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] decoder = Base64.getMimeDecoder().decode(base64);
            fos.write(decoder);
            System.out.println("PDF File Saved");
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }
}

