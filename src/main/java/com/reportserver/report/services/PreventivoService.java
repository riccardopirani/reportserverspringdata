package com.reportserver.report.services;

import com.reportserver.report.enums.PreventivoCalcoloScontoType;
import com.reportserver.report.enums.ReportType;
import com.reportserver.report.model.CapitoloPreventivo;
import com.reportserver.report.model.Cliente;
import com.reportserver.report.model.Preventivo;
import com.reportserver.report.model.TableReportPreventivo;
import com.reportserver.report.repository.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PreventivoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
    @Autowired
    private AziendaRepository repoAzienda;
    @Autowired
    private PreventivoRepository repoPreventivo;
    @Autowired
    private ClienteRepository repoCliente;

    /*@Autowired
    private CapitoloRepository repoCapitolo; */
    @Autowired
    private SottoCapitoloRepository repoSottoCapitolo;
    @Autowired
    private CapitoloPreventivoRepository repoCapitoloPreventivo;

    /**
     * Funzione che si occupa della stampa di un preventivo
     *
     * @param p
     * @param rp
     * @param typeCalcoloSconto
     * @return
     */
    @Transactional
    public Preventivo stampa(Preventivo p, ReportType rp, PreventivoCalcoloScontoType typeCalcoloSconto) {
        //Carico le infomazioni di base necessarie per la stampa del preventivo
        Preventivo ptemp = p;
        ptemp = repoPreventivo.getInfo(p.getIdPreventivo());
        p = ptemp;
        Cliente c = repoCliente.getOne(ptemp.getIdCliente());
        try {
            if (rp == ReportType.STAMPADISTINAARTICOLICOSTIGLOBALE) {
                List<CapitoloPreventivo> listaCapitoloPreventivo = repoCapitoloPreventivo.load(p.getIdPreventivo());
                for (CapitoloPreventivo temp : listaCapitoloPreventivo) {
                    LOGGER.info("temp: " + temp.getCodArt());
                }
            } else if (rp == ReportType.STAMPAPREVENTIVOCOMPLETO) {
                List<TableReportPreventivo> tablevaluesList = new ArrayList<>();
                //Caricamento  capitolo
                /*List<Capitolo> listaCapitolo = repoCapitolo.loadByIdPreventivo(ptemp.getIdPreventivo());
                for (int i = 0; i < listaCapitolo.size(); i++) {
                    List<SottoCapitolo> listSottoCapitoli = repoSottoCapitolo.load(listaCapitolo.get(i).getIdCapitolo());
                    for (int i2 = 0; i2 < listSottoCapitoli.size(); i2++) {
                        System.out.println("Capitolo: " + listaCapitolo.get(i).getDescrizione() + " \n Descrizione Sotto Capitolo: " + listSottoCapitoli.get(i2).getDescrizione());
                    }
                }
                //Caricamento valori con unica percentuale
                if (typeCalcoloSconto.name().equals("UNICAPERCENTUALE")) {
                    Stream<List<TableReportCapitoloPreventivo>> listSottoCapitoliStream = repoSottoCapitolo.loadValoriPreventivoUnicaPercentuale(ptemp.getIdPreventivo());
                    listSottoCapitoliStream.forEach(value -> {
                        // System.out.println("Value value: "+value.get(0));
                    });

                }*/
                //Carico le informazioni del preventivo del Preventivo
                Date dt = ptemp.getDataInserimento();
                Date dat = java.sql.Date.valueOf(String.valueOf(dt));
                Calendar cal = Calendar.getInstance();
                cal.setTime(dat);
                TableReportPreventivo tablevalues = new TableReportPreventivo(dt.toString(), ptemp.getIdPreventivo() + "/" + cal.get(Calendar.YEAR), ptemp.getRiferimentoInterno(), ptemp.getVostroRiferimento(), c.getTitolo(), c.getRagioneSociale(), c.getIndirizzo(), c.getCap() + " " + c.getCitta() + " " + c.getProvincia(), ptemp.getOggetto(), ptemp.getTestata(), "");
                ClassPathResource reportResource = new ClassPathResource("templates/reportpreventivo.jasper");
                Map<String, Object> reportParameters = new HashMap<>();
                tablevaluesList.add(tablevalues);
                JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(tablevaluesList);
                reportParameters.put("Data", ptemp.getDataInserimento().toString());
                reportParameters.put("NRPreventivo", ptemp.getIdPreventivo() + "/" + cal.get(Calendar.YEAR));
                reportParameters.put("NSRif", ptemp.getRiferimentoInterno());
                reportParameters.put("VSRif", ptemp.getVostroRiferimento());
                reportParameters.put("Titolo", c.getTitolo());
                reportParameters.put("RagioneSociale", c.getRagioneSociale());
                reportParameters.put("Indirizzo", c.getIndirizzo());
                reportParameters.put("Citta", c.getCitta());
                reportParameters.put("Testata", p.getTestata());
                reportParameters.put("Oggetto", p.getOggetto());
                //reportParameters.put("LogoBase64", base64image);
                ReportService.exportReportToPDF(reportResource.getInputStream(), reportParameters, "report");
                if (rp.name().equals("STAMPAPREVENTIVOCOMPLETO")) {
                    System.out.println("Stampa preventivo completo");
                } else {
                    System.out.println("NO è Stampa preventivo completo");
                }
            }

        } catch (Exception ex) {
            System.out.println("Errore: " + ex);

        }
        return ptemp;
    }
}
