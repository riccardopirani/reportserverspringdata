package com.reportserver.report.services;

import com.reportserver.report.model.Impianto;
import com.reportserver.report.model.Noteimpianto;
import com.reportserver.report.model.TableReportNote;
import com.reportserver.report.repository.NoteimpiantoRepository;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {

    @Autowired
    private NoteimpiantoRepository repo;

    public Boolean generateReport(Impianto imp) {
        try {
            List<TableReportNote> tablevalues = new ArrayList<>();
            List<Noteimpianto> notelis = repo.findbyIdImpianto(imp.getIdImpianto());
            for (int i = 0; i < notelis.size(); i++) {
                TableReportNote tp = new TableReportNote(notelis.get(i).getTesto());
                tablevalues.add(tp);
            }
            ClassPathResource reportResource = new ClassPathResource("templates/reportnote.jasper");
            Map<String, Object> reportParameters = new HashMap<>();
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(tablevalues);
            reportParameters.put("ItemDataSource", itemsJRBean);
            reportParameters.put("NumeroImpianto", "Numero Impianto: "+imp.getIdImpianto());
            ReportService.exportReportToPDF(reportResource.getInputStream(), reportParameters,"note");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}