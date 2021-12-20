package com.reportserver.report.resources;

import com.reportserver.report.model.Cantiere;
import com.reportserver.report.model.Ristorante;
import com.reportserver.report.repository.RistoranteRepository;
import com.reportserver.report.services.CantiereService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Description(value = "Report Ristoranti")
@RestController
@RequestMapping("/api/report/ristoranti")
public class RistoranteResource {

    private final CantiereService cservice;
    /**
     * Definizione del rapporto repository
     */
    @Autowired
    private RistoranteRepository reposi;

    /**
     * Constructor dependency injector.
     *
     * @param cservice - report service dependency
     */
    public RistoranteResource(CantiereService cservice) {
        this.cservice = cservice;
    }

    /**
     * Endpoint che effettua il caricamento dei ristoranti presenti in cantiere
     *
     * @param cantiere - rappresenta il cantiere passatto come parametro al json
     * @return byte array resource (generated file in bytes)
     */

    @PostMapping(value = "/caricamento", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> caricamentoristoranti(@Valid @RequestBody Cantiere cantiere) throws JSONException {
        List<Ristorante> listlp = reposi.findbycantiere(cantiere.getIdCantiere());
        return new ResponseEntity<>(listlp, HttpStatus.OK);
    }
}
