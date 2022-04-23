package com.reportserver.report.services;

import com.reportserver.report.enums.ListiniType;
import com.reportserver.report.model.Access;
import com.reportserver.report.model.Articolo;
import com.reportserver.report.model.FamigliaDiSconto;
import com.reportserver.report.model.Produttore;
import com.reportserver.report.repository.ArticoloRepository;
import com.reportserver.report.repository.ProduttoreRepository;
import com.reportserver.report.utils.Support;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service che si occupa della gestione degli articoli
 */
@Service
public class ArticoloService {
    /**
     * Parametro che viene settato all'importazione degli articoli
     */
    @Autowired
    public static Boolean checkImportazione = false;
    @Autowired
    private ArticoloRepository repoArticoli;
    @Autowired
    private ProduttoreRepository repoProduttore;
    @Autowired
    private SendMailService SendMailService;

    /**
     * Funzione che calcola gli sconti per produttore per famiglia di sconto
     *
     * @param IdFornitore
     * @param Fornitore
     * @return
     */
    public Boolean calcoloScontiProduttore(int IdFornitore, String Fornitore) {
        //recupero le famiglie di sconto associate al produttore
        List<Produttore> listProduttore = repoProduttore.get(IdFornitore);
        for (Produttore ptemp : listProduttore) {
            //Per ciascun produttore calcolo lo sconto sugli articoli associations
            String codMarca = ptemp.getCodMarca();
            String fm = ptemp.getFamigliaDiSconto();
            repoArticoli.updateArticoloMetelScontoFirst(codMarca, Fornitore, fm);
            repoArticoli.updateArticoloMetelScontoSecond(codMarca, Fornitore, fm);
            repoArticoli.updateArticoloMetelScontoThird(codMarca, Fornitore, fm);
        }
        return true;
    }

    /**
     * Funzione che si occupa di aggiornare gli sconti degli articoli per produttore
     *
     * @param Sconto1
     * @param Sconto2
     * @param Sconto3
     * @param IdFornitore
     * @param FamigliaDiSconto
     * @return
     */
    public Boolean aggiornascontoproduttore(double Sconto1, double Sconto2, Double Sconto3, int IdFornitore, String FamigliaDiSconto) {
        try {
            if (FamigliaDiSconto.equals("Tutti")) {
                repoProduttore.updateByFornitore(Sconto1, Sconto2, Sconto3, IdFornitore);
            } else {
                repoProduttore.update(Sconto1, Sconto2, Sconto3, IdFornitore, FamigliaDiSconto);
            }
            calcoloScontiProduttore(IdFornitore, "METEL");
            return true;
        } catch (Exception ex) {
            System.out.println("Errore: " + ex);
            return false;
        }
    }

    /**
     * Funzione che si occupa dell'importazione dei listini
     *
     * @param Fornitore
     * @param filename
     * @param ltype
     * @param Password
     * @param EmailUtente
     * @param IdFornitore
     * @param IdUtente
     * @return
     */
    public Boolean importalistino(String Fornitore, MultipartFile filename, ListiniType ltype, String Password, String EmailUtente, int IdFornitore, int IdUtente) {
        Logger logger = LogManager.getRootLogger();
        if (ltype.name().equals("MDB")) {
            try {
                List<Articolo> listaArticoliFile = Access.readfile(Support.tempPath + "/" + filename.getOriginalFilename(), ltype, "");
                List<Articolo> listaArticoliDatabase = repoArticoli.findforimportMDB(Fornitore);
                //Eseguo l'aggiornamento degli articoli già presenti
                for (Articolo atemp : listaArticoliFile) {
                    checkImportazione = true;
                    //se l'ulemento è presente nel db eseguo l'aggiornamento dell'articolo in caso negativo eseguo l'inserimento
                    if (listaArticoliDatabase.stream().filter(item -> item.getCodart().equals(atemp.getCodart())).count() > 0) {
                        repoArticoli.updateArticolo("Disponibile", atemp.getDescrizione(), atemp.getPrezzo(), atemp.getPrezzo(), Fornitore, atemp.getCodart(), "MDB", BigInteger.ONE);
                    } else {
                        repoArticoli.insert(atemp.getCodart(), atemp.getPrezzo(), atemp.getDescrizione(), "MDB", Fornitore, "A", "PZ", 1, "Disponibile", "", atemp.getPrezzo(), "", BigInteger.ONE);
                    }
                }
            } catch (Exception ex) {
                logger.info("Errore importazione MDB: " + ex);
                return false;
            }

        } else if (ltype.name().equals("COMET")) {
            try {
                List<Articolo> listaArticoliFile = Access.readfile(Support.tempPath + "/" + filename.getOriginalFilename(), ltype, Password);
                List<Articolo> listaArticoliDatabase = repoArticoli.findforimportCOMET();
                //Setto gli articoli come non disponbili
                repoArticoli.setNonDisponbileCOMET();
                //Eseguo l'aggiornamento degli articoli già presenti
                for (Articolo atemp : listaArticoliFile) {
                    checkImportazione = true;
                    //se l'elemento è presente nel db eseguo l'aggiornamento dell'articolo in caso negativo eseguo l'inserimento
                    if (listaArticoliDatabase.stream().filter(item -> item.getCodart().equals(atemp.getCodart())).count() > 0) {
                        repoArticoli.updateArticolo("Disponibile", atemp.getDescrizione(), atemp.getPrezzo(), atemp.getPrezzolistino(), "COMET", atemp.getCodart(), "COMET", BigInteger.ZERO);
                    } else {
                        repoArticoli.insert(atemp.getCodart(), atemp.getPrezzo(), atemp.getDescrizione(), "COMET", "COMET", "A", atemp.getUm(), 1, "Disponibile", atemp.getCodmarca(), atemp.getPrezzolistino(), "", BigInteger.ONE);
                    }
                }
            } catch (Exception ex) {
                logger.info("Errore importazione COMET: " + ex);
                return false;
            }
        } else {
            try {
                List<Articolo> listaArticoliFile = Access.readfile(Support.tempPath + "/" + filename.getOriginalFilename(), ltype, "");
                List<FamigliaDiSconto> listfamiglie = new ArrayList<>();
                //Eseguo l'aggiornamento degli articoli già presenti
                for (Articolo atemp : listaArticoliFile) {
                    checkImportazione = true;
                    //se l'elemento è presente nel db eseguo l'aggiornamento dell'articolo in caso negativo eseguo l'inserimento
                    if (repoArticoli.countArticoloMetel(atemp.getCodmarca(), atemp.getCodart()).size() > 0) {
                        repoArticoli.updateArticolo("Disponibile", atemp.getDescrizione(), atemp.getPrezzo(), atemp.getPrezzolistino(), atemp.getCodmarca(), atemp.getCodart(), "METEL", atemp.getCodEAN());
                    } else {
                        repoArticoli.insert(atemp.getCodart(), atemp.getPrezzo(), atemp.getDescrizione(), "METEL", atemp.getCodmarca(), "A", atemp.getUm(), 1, "Disponibile", atemp.getCodmarca(), atemp.getPrezzolistino(), atemp.getFamigliadisconto(), atemp.getCodEAN());
                    }
                    FamigliaDiSconto fm2 = new FamigliaDiSconto();
                    fm2.setFamigliaDisconto(atemp.getFamigliadisconto());
                    fm2.setCodMarcaCode(atemp.getCodmarca());
                    if (!listfamiglie.contains(fm2)) {
                        listfamiglie.add(fm2);
                    }
                }
                //inserisco le nuove famiglie di sconto
                listfamiglie = listfamiglie.stream().distinct().collect(Collectors.toList());
                for (FamigliaDiSconto ftemp2 : listfamiglie) {
                    if (repoProduttore.contafamigliedisconto(ftemp2.getFamigliaDisconto(), ftemp2.getCodMarcaCode(), IdFornitore) > 0) {
                        logger.info(ftemp2.getCodMarcaCode());
                        logger.info(ftemp2.getFamigliaDisconto());
                    } else {
                        repoProduttore.insert(IdFornitore, ftemp2.getFamigliaDisconto(), 0, 0, 0, ftemp2.getCodMarcaCode(), IdUtente);
                    }
                }
                //Eseguo il calcolo dei nuove sconti
                calcoloScontiProduttore(IdFornitore, "METEL");
            } catch (Exception ex) {
                logger.info("Errore importazione METEL: " + ex);
                return false;
            }

        }
        checkImportazione = false;
        return true;
    }
}
