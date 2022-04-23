package com.reportserver.report.resources;

import com.reportserver.report.model.Cantiere;
import com.reportserver.report.model.CantierePrezzo;
import com.reportserver.report.repository.RistoranteRepository;
import com.reportserver.report.services.CantiereService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Description(value = "Report Cantiere")
@RestController
@RequestMapping("/api/report/cantiere")
public class CantiereResource {

    /**
     * Parameter of ristorante
     *
     * @param cservice - cantiere service dependency
     */
    private final CantiereService cservice;
    /**
     * Parameter of ristorante
     *
     * @param reposi - ristorante service dependency
     */
    @Autowired
    private RistoranteRepository reposi;

    /**
     * Constructor dependency injector.
     *
     * @param cservice - cantiere service dependency
     */
    public CantiereResource(CantiereService cservice) {
        this.cservice = cservice;
    }

    /**
     * Endpoint that deals with the printing of the construction site no price and no price
     *
     * @return byte array resource (generated file in bytes)
     */
    @PostMapping(value = "/print/price", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> printprice(HttpServletResponse response, @Valid @RequestBody CantierePrezzo cantiere, @RequestParam(required = true, defaultValue = "false") Boolean isPrice, @RequestParam(required = true, defaultValue = "false") Boolean printArticoli, @RequestParam(required = true, defaultValue = "false") Boolean printRistoranti, @RequestParam(required = true, defaultValue = "false") Boolean printSpese, @RequestParam(required = true, defaultValue = "false") Boolean printNoleggi, @RequestParam(required = true, defaultValue = "false") Boolean printRisorse, @RequestParam(required = true, defaultValue = "false") Boolean printKilometri, @RequestParam(required = true, defaultValue = "false") Boolean printDiritto, @RequestParam(required = true, defaultValue = "false") Boolean isCostoUnicoRisorsa, @RequestParam(required = true, defaultValue = "0") double prezzounicorisorsa) throws JSONException, IOException {
        Cantiere c = new Cantiere();
        c.setIdCantiere(cantiere.IdCantiere);
        c.setNomeCantiere(cantiere.NomeCantiere);
        c.setCommessa(cantiere.Commessa);
        c.setRagioneSociale(cantiere.Commessa);
        c.setLastName(cantiere.RagioneSociale);
        c.setStatoCantiere(cantiere.StatoCantiere);

        try {
            Boolean check = cservice.generateReport(c, isPrice, cantiere.Ricarico, printArticoli, printRistoranti, printSpese, printNoleggi, printRisorse, printKilometri, printDiritto, isCostoUnicoRisorsa, prezzounicorisorsa);
            return new ResponseEntity<>(check, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid Ricarico", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint return report file pdf
     *
     * @return Pdf file of report cantiere
     */
    @GetMapping(value = "/getfile.pdf")
    public ResponseEntity<ByteArrayResource> download(String param) throws IOException {
        File file;
        file = new File("/var/tmp/report.pdf");
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
