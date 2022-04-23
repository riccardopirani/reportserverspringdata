package com.reportserver.report.model;

import com.reportserver.report.enums.ListiniType;
import com.reportserver.report.resources.ReportResource;
import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che si occupa della lettura dei file
 */
public class Access {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReportResource.class);

    /**
     * Funzione che si occupa di leggere i file dei listini
     *
     * @param nomefile
     * @param tipoListino
     * @param password
     * @return
     */
    public static List<Articolo> readfile(String nomefile, ListiniType tipoListino, String password) {

        List<Articolo> listaArticoli = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        org.apache.logging.log4j.Logger logger = LogManager.getRootLogger();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
        try {
            if (tipoListino.name().equals("MDB")) {
                String dbURL = "jdbc:ucanaccess://" + nomefile;
                connection = DriverManager.getConnection(dbURL);
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM Articoli");
                while (resultSet.next()) {
                    try {
                        Articolo ltemp = new Articolo(resultSet.getString(1), resultSet.getString(2), Double.parseDouble(resultSet.getString(3).replaceAll(",", ".")));
                        listaArticoli.add(ltemp);
                    } catch (Exception err) {
                        System.out.println("Errroe lettura riga file: " + err);
                    }
                }
            } else if (tipoListino.name().equals("COMET")) {
                String dbURL = "jdbc:ucanaccess://" + nomefile;
                connection = DriverManager.getConnection(dbURL, "", password);
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM ARTICOLI");
                while (resultSet.next()) {
                    try {
                        Articolo ltemp = new Articolo(resultSet.getString(1), resultSet.getString(2), resultSet.getString(5), resultSet.getString(10), Double.parseDouble(resultSet.getString(17)), Double.parseDouble(resultSet.getString(18)), "0", "", 0, BigInteger.ONE);
                        listaArticoli.add(ltemp);
                    } catch (Exception err) {
                        System.out.println("Errroe lettura riga file: " + err);
                    }
                }

            }
            //Lettura di un file METEL
            else {
                File file = new File(nomefile);
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line = br.readLine();
                    line = br.readLine();
                    while (line != null) {
                        BigInteger CodEAN = BigInteger.ONE;
                        int value = 1;
                        try {
                            String CodEAN_String = line.substring(19, 32).replaceAll(" ", "");
                            CodEAN = BigInteger.valueOf(Long.parseLong((CodEAN_String)));
                        } catch (Exception ex) {
                            System.out.println("Codice EAN non presente: " + ex);
                        }
                        //value = Integer.parseInt(line.substring(122, 125));
                        value = Integer.parseInt(line.substring(120, 125));
                        double Prezzo = 0;
                        String output = line.substring(97, 108);
                        for (; output.length() > 1 && output.charAt(0) == '0'; output = output.substring(1)) ;
                        Prezzo = ((Double.parseDouble(output) / 100) / value);
                        Articolo ltemp = new Articolo(line.substring(0, 3), line.substring(3, 19).replaceFirst("\\s++$", ""), line.substring(32, 70), line.substring(128, 131).replaceFirst("\\s++$", ""), Prezzo, Prezzo, line.substring(21, 34), line.substring(141, 159).replaceFirst("\\s++$", ""), value, CodEAN);
                        listaArticoli.add(ltemp);
                        line = br.readLine();
                    }
                } catch (Exception e) {
                    System.out.println("Errore lettura file txt: " + e);
                    e.printStackTrace();
                }
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    assert resultSet != null;
                    resultSet.close();
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                }
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
        return listaArticoli;
    }


}
