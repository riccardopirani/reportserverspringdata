package com.reportserver.report.resources;

import com.reportserver.report.repository.RapportoRepository;
import com.reportserver.report.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Description(value = "Report Cantieri")
@RestController
@RequestMapping("/api/report")
public class ReportResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportResource.class);

    private final ReportService reportService;
    /**
     * Definizione del rapporto repository
     */
    @Autowired
    private RapportoRepository rapportoRepository;

    /**
     * Constructor dependency injector.
     *
     * @param reportService - report service dependency
     */
    public ReportResource(ReportService reportService) {
        this.reportService = reportService;
    }


}
