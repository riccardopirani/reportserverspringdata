package com.reportserver.report.services;

import com.reportserver.report.model.*;
import com.reportserver.report.repository.*;
import com.reportserver.report.utils.Support;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CantiereService {

    @Autowired
    private RistoranteRepository repo;

    @Autowired
    private ArticoloCantiereRepository repoArticoli;

    @Autowired
    private RisorseUmaneRepository repoRisorse;

    @Autowired
    private KilometriRepository repoKilometri;

    @Autowired
    private NoleggioRepository repoNoleggi;

    @Autowired
    private UtenteRepository repoutente;

    @Autowired
    private ClienteRepository repocliente;

    @Autowired
    private SpeseSostenuteRepository repospese;

    /**
     * Function generate report
     * <p>
     * Cantiere
     * isPrice define if price is visibile or not
     * Ricarco is
     *
     * @return boolean
     */
    public Boolean generateReport(Cantiere c, boolean isPrice, double Ricarico, boolean printArticoli, boolean printRistoranti, boolean printSpese, boolean printNoleggi, boolean printRisorse, boolean printKilometri, boolean printDiritto, boolean isCostoUnicoRisorsa, double CostoUnicoRisorsa) {
        try {
            Cliente ctemp = new Cliente();
            List<TableReport> tablevalues = new ArrayList<>();
            List<Ristorante> ristoranti = repo.findbycantiere(c.getIdCantiere());
            List<ArticoloCantiere> articolicantere = repoArticoli.findbycantiere(c.getIdCantiere());
            List<RisorseUmane> risorsecantiere = repoRisorse.findbycantiere(c.getIdCantiere());
            List<Kilometri> kilometri = repoKilometri.findbycantiere(c.getIdCantiere());
            List<Noleggio> noleggi = repoNoleggi.findbycantiere(c.getIdCantiere());
            List<SpeseSostenute> spese = repospese.findbycantiere(c.getIdCantiere());
            double totaleristoranti = 0, totalenoleggi = 0, totalekiometri = 0, totalespesesostenute = 0, totalearticoli = 0, totalerisorse = 0;
            String totaleore = "0";
            try {
                ctemp = repocliente.findbyIdCliente(c.getIdCliente());
            } catch (Exception ex) {
                System.out.println("Not defined");
            }
            TableReport tp;
            if(printRistoranti) {
                //Insert value of ristoranti into tale tablevalues
                for (int i = 0; i < ristoranti.size(); i++) {

                    if (isPrice) {
                        if (Ricarico > 0) {
                            tp = new TableReport("", "RS", ristoranti.get(i).getDatainserimento(), Support.convertDataToString(ristoranti.get(i).getDatainserimento()), "", "" + Support.rechargepercentage(Ricarico, ristoranti.get(i).getcosto()), ristoranti.get(i).getRagionesociale(), "" + Support.rechargepercentage(Ricarico, ristoranti.get(i).getcosto()), "0");
                            totaleristoranti = totaleristoranti + Support.rechargepercentage(Ricarico, ristoranti.get(i).getcosto());
                        } else {
                            totaleristoranti = totaleristoranti + ristoranti.get(i).getcosto();
                            tp = new TableReport("", "RS", ristoranti.get(i).getDatainserimento(), Support.convertDataToString(ristoranti.get(i).getDatainserimento()), "", "" + ristoranti.get(i).getcosto(), ristoranti.get(i).getRagionesociale(), "" + ristoranti.get(i).getcosto(), "0");
                        }
                    } else {
                        tp = new TableReport("", "RS", ristoranti.get(i).getDatainserimento(), Support.convertDataToString(ristoranti.get(i).getDatainserimento()), "", "", ristoranti.get(i).getRagionesociale(), "", "");
                    }

                    tablevalues.add(tp);
                }
            }

            if(printSpese){
                for (int i = 0; i < spese.size(); i++) {

                    if (isPrice) {
                        if (Ricarico > 0) {
                            tp = new TableReport("", "S", spese.get(i).getData().toString(), Support.convertDataToString(spese.get(i).getData().toString()), "", "" + Support.rounddouble2decimal(Support.rechargepercentage(Ricarico, spese.get(i).getCosto())), spese.get(i).getCasuale(), "" + Support.rounddouble2decimal(Support.rounddouble2decimal(Support.rechargepercentage(Ricarico, spese.get(i).getCosto()))), "");
                            totalespesesostenute = totalespesesostenute + Support.rounddouble2decimal(Support.rechargepercentage(Ricarico, spese.get(i).getCosto()));
                        } else {
                            tp = new TableReport("", "S", spese.get(i).getData().toString(), Support.convertDataToString(spese.get(i).getData().toString()), "", "" + Support.rounddouble2decimal(spese.get(i).getCosto()), spese.get(i).getCasuale(), "" + Support.rounddouble2decimal(Support.rounddouble2decimal(spese.get(i).getCosto())), "");
                            totalespesesostenute = totalespesesostenute + Support.rounddouble2decimal(Support.rechargepercentage(0, spese.get(i).getCosto()));
                        }

                    } else {
                        tp = new TableReport("", "S", spese.get(i).getData().toString(), Support.convertDataToString(spese.get(i).getData().toString()), "", "", spese.get(i).getCasuale(), "", "");
                    }

                    tablevalues.add(tp);
                }
            }
            if (printArticoli) {
                //Inset value of articoli into tablevalues
                for (int i = 0; i < articolicantere.size(); i++) {
                    if (isPrice) {
                        if (Ricarico > 0) {
                            tp = new TableReport("", "A", articolicantere.get(i).getData().toString(), Support.convertDataToString(articolicantere.get(i).getData().toString()), articolicantere.get(i).getCodart(), "" + Support.rounddouble2decimal(Support.rechargepercentage(Ricarico, articolicantere.get(i).getPrezzo())), articolicantere.get(i).getDescrizione(), "" + Support.rounddouble2decimal(Support.rounddouble2decimal(Support.rechargepercentage(Ricarico, articolicantere.get(i).getPrezzo())) * articolicantere.get(i).getQuantita()), "" + articolicantere.get(i).getQuantita());
                            totalearticoli = totalearticoli + (Support.rechargepercentage(Ricarico, articolicantere.get(i).getPrezzo()) * articolicantere.get(i).getQuantita());
                        } else {
                            tp = new TableReport("", "A", articolicantere.get(i).getData().toString(), Support.convertDataToString(articolicantere.get(i).getData().toString()), articolicantere.get(i).getCodart(), "" + Support.rounddouble2decimal(articolicantere.get(i).getPrezzo()), articolicantere.get(i).getDescrizione(), "" + Support.rounddouble2decimal(Support.rounddouble2decimal(articolicantere.get(i).getPrezzo()) * articolicantere.get(i).getQuantita()), "" + articolicantere.get(i).getQuantita());
                            totalearticoli = totalearticoli + (articolicantere.get(i).getPrezzo() * articolicantere.get(i).getQuantita());
                        }

                    } else {
                        tp = new TableReport("", "A", articolicantere.get(i).getData().toString(), Support.convertDataToString(articolicantere.get(i).getData().toString()), articolicantere.get(i).getCodart(), "", articolicantere.get(i).getDescrizione(), "", "" + articolicantere.get(i).getQuantita());
                    }

                    tablevalues.add(tp);
                }
            }
            if(printRisorse) {
                try {
                    //Inset value of risorse umane into tablevalues
                    for (int i = 0; i < risorsecantiere.size(); i++) {
                        Utente utemp = repoutente.findbyIdUtente(risorsecantiere.get(i).getIdutente());
                        totaleore = repoRisorse.gettotaleore(c.IdCantiere, utemp.getIdutente());
                        double orecast = Double.parseDouble(totaleore);
                        totaleore = totaleore.replace(".", ":");
                        totaleore = totaleore.substring(0, totaleore.length() - 2);
                        if (isPrice) {

                            if (isCostoUnicoRisorsa) {
                                tp = new TableReport("", "R", risorsecantiere.get(i).getData().toString(), Support.convertDataToString(risorsecantiere.get(i).getData().toString()), utemp.getNome() + " " + utemp.getCognome(), "" + CostoUnicoRisorsa, risorsecantiere.get(i).getDescrizione(), ""
                                        + (orecast * CostoUnicoRisorsa), totaleore);
                            } else {
                                tp = new TableReport("", "R", risorsecantiere.get(i).getData().toString(), Support.convertDataToString(risorsecantiere.get(i).getData().toString()), utemp.getNome() + " " + utemp.getCognome(), "" + utemp.getCostointerno(), risorsecantiere.get(i).getDescrizione(), ""
                                        + (orecast * utemp.getCostointerno()), totaleore);
                            }

                        } else {
                            tp = new TableReport("", "R", risorsecantiere.get(i).getData().toString(), Support.convertDataToString(risorsecantiere.get(i).getData().toString()), utemp.getNome() + " " + utemp.getCognome(), "", risorsecantiere.get(i).getDescrizione(), "", totaleore);
                        }
                        totalerisorse = orecast * utemp.getCostointerno();
                        tablevalues.add(tp);
                    }
                } catch (Exception ex) {
                    System.out.println("Errore: " + ex);
                }
            }
            if(printKilometri) {
                //Inset value of kilometri into tablevalues
                for (int i = 0; i < kilometri.size(); i++) {
                    if (isPrice) {
                        tp = new TableReport("", "K", kilometri.get(i).getData().toString(), Support.convertDataToString(kilometri.get(i).getData().toString()), "Kilometri", "" + ctemp.getRimborsoKM(), "", "", "" + (kilometri.get(i).getKilometri() * ctemp.getRimborsoKM()));
                        totalekiometri = totalekiometri + (kilometri.get(i).getKilometri() * ctemp.getRimborsoKM());
                    } else {
                        tp = new TableReport("", "K", kilometri.get(i).getData().toString(), Support.convertDataToString(kilometri.get(i).getData().toString()), "Kilometri", "", "", "", "" + kilometri.get(i).getKilometri());

                    }
                    tablevalues.add(tp);
                }
            }
            if(printNoleggi) {
                //Inset value of noleggi into tablevalues
                for (int i = 0; i < noleggi.size(); i++) {
                    if (isPrice) {
                        tp = new TableReport("", "N", noleggi.get(i).getDatainizionoleggio().toString(), Support.convertDataToString(noleggi.get(i).getDatainizionoleggio().toString()), "Noleggi", "", "Fornitore: " + " " + "Tipo Mezzo: " + noleggi.get(i).getTipomezzo() + " Date Noleggio: " + noleggi.get(i).getDatainizionoleggio() + " - " + noleggi.get(i).getDataterminenoleggio(), "" + noleggi.get(i).getCostonoleggio(), "");
                        totalenoleggi = totalenoleggi + noleggi.get(i).getCostonoleggio();
                    } else {
                        tp = new TableReport("", "N", noleggi.get(i).getDatainizionoleggio().toString(), Support.convertDataToString(noleggi.get(i).getDatainizionoleggio().toString()), "Noleggi", "", "Fornitore: " + " " + "Tipo Mezzo: " + noleggi.get(i).getTipomezzo() + " Date Noleggio: " + noleggi.get(i).getDatainizionoleggio() + " - " + noleggi.get(i).getDataterminenoleggio(), "", "" + "");
                    }
                    tablevalues.add(tp);
                }
            }


            ClassPathResource reportResource = new ClassPathResource("templates/printcantiere.jasper");
            Map<String, Object> reportParameters = new HashMap<>();
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(tablevalues);
            reportParameters.put("ItemDataSource", itemsJRBean);
            reportParameters.put("RagioneSociale", c.getRagioneSociale());
            reportParameters.put("Commessa", c.getCommessa());
            reportParameters.put("NomeCantiere", c.getNomeCantiere());
            reportParameters.put("Stato", c.getStatoCantiere());
            reportParameters.put("TotaleOreRisorsa", totaleore);
            if (!isPrice) {
                reportParameters.put("TotaleRisorse", "");
                reportParameters.put("TotaleArticoli", "");
                reportParameters.put("TotaleSpese", "");
                reportParameters.put("TotaleRistoranti", "");
                reportParameters.put("TotaleDirittoChiamata", "");
                reportParameters.put("TotaleKilometri", "");
                reportParameters.put("TotaleNoleggi", "");
                reportParameters.put("TotaleCantiere", "");
            } else {
                reportParameters.put("TotaleRisorse", "" + totalerisorse + " €");
                reportParameters.put("TotaleArticoli", "" + totalearticoli + " €");
                reportParameters.put("TotaleSpese", "" + totalespesesostenute + " €");
                reportParameters.put("TotaleRistoranti", "" + totaleristoranti + " €");
                reportParameters.put("TotaleDirittoChiamata", "0");
                reportParameters.put("TotaleKilometri", "" + totalekiometri + " €");
                reportParameters.put("TotaleNoleggi", "" + totalenoleggi + " €");
                reportParameters.put("TotaleCantiere", "" + (totalenoleggi + totalerisorse + totalekiometri + totaleristoranti + totalespesesostenute + totalearticoli) + " €");
            }
            reportParameters.put("CommessaCliente", "");
            ReportService.exportReportToPDF(reportResource.getInputStream(), reportParameters,"report");
            ReportService.exportReportToDOCx(reportResource.getInputStream(), reportParameters);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}