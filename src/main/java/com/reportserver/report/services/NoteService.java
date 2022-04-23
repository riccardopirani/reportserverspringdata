package com.reportserver.report.services;

import com.reportserver.report.model.Impianto;
import com.reportserver.report.model.Noteimpianto;
import com.reportserver.report.model.TableReportNote;
import com.reportserver.report.model.Utente;
import com.reportserver.report.repository.NoteimpiantoRepository;
import com.reportserver.report.repository.UtenteRepository;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe che su occupa di fornire le api per la gestione delle note
 */
@Service
public class NoteService {

    @Autowired
    private NoteimpiantoRepository repo;
    @Autowired
    private UtenteRepository repoUtente;

    /**
     * Generazione report impianto
     *
     * @param imp
     * @return
     */
    public Boolean generateReport(Impianto imp) {
        try {
            List<TableReportNote> tablevalues = new ArrayList<>();
            List<Noteimpianto> notelis = repo.findbyIdImpianto(imp.getIdImpianto());
            for (int i = 0; i < notelis.size(); i++) {
                //Get user id
                Utente u = repoUtente.getOne(notelis.get(i).getIdUtente());
                TableReportNote tp = new TableReportNote("  Nota: " + notelis.get(i).getTesto() + " - Data: " + notelis.get(i).getDatainserimento() + " - Utente: " + u.getNome() + " " + u.getCognome());
                tablevalues.add(tp);
            }
            ClassPathResource reportResource = new ClassPathResource("templates/reportnote.jasper");
            Map<String, Object> reportParameters = new HashMap<>();
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(tablevalues);
            reportParameters.put("ItemDataSource", itemsJRBean);
            reportParameters.put("NumeroImpianto", "Numero Impianto: " + imp.getIdImpianto());
            ReportService.exportReportToPDF(reportResource.getInputStream(), reportParameters, "note");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}