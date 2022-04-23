package com.reportserver.report.resources;

import com.reportserver.report.enums.PreventivoCalcoloScontoType;
import com.reportserver.report.enums.ReportType;
import com.reportserver.report.model.Preventivo;
import com.reportserver.report.model.ResponseMessage;
import com.reportserver.report.services.PreventivoService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Description(value = "Report Preventivo")
@RestController
@RequestMapping("/api/preventivo")
public class PreventivoResource {

    @Autowired
    PreventivoService preventivoService;

    /**
     * Endpoint print of report java stampa completa
     *
     * @return byte array resource (generated file in bytes)
     */
    @GetMapping(value = "/report/completo")
    public @ResponseBody
    ResponseEntity<Object> preventivocompleto(@RequestParam(required = true, value = "idPreventivo") int idPreventivo, @RequestParam(required = true, value = "MODALITASCONTO") PreventivoCalcoloScontoType MODALITASCONTO) throws JSONException {
        try {
            Preventivo p = preventivoService.stampa(new Preventivo(idPreventivo), ReportType.STAMPAPREVENTIVOCOMPLETO, MODALITASCONTO);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage().toString() + " Cause: " + e.getCause().getMessage()));
        }
    }

    /**
     * Endpoint print of report java stampa distinta articoli e costi globale
     *
     * @return byte array resource (generated file in bytes)
     */
    @GetMapping(value = "/report/articolocostiglobale")
    public @ResponseBody
    ResponseEntity<Object> stampadistintaarticolicostiglobali(@RequestParam(required = true, value = "idPreventivo") int idPreventivo, @RequestParam(required = true, value = "MODALITASCONTO") PreventivoCalcoloScontoType MODALITASCONTO) throws JSONException {
        try {
            Preventivo p = preventivoService.stampa(new Preventivo(idPreventivo), ReportType.STAMPADISTINAARTICOLICOSTIGLOBALE, MODALITASCONTO);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage().toString() + " Cause: " + e.getCause().getMessage()));
        }
    }

    /**
     * Endpoint print of report java stampa distinta articoli e costi globali
     *
     * @return byte array resource (generated file in bytes)
     */
    @GetMapping("/report/stampadistintaarticolicostiglobali")
    public ResponseEntity<ResponseMessage> stampadistintaarticolicostiglobali(@RequestParam("file") MultipartFile file, @RequestParam(required = true, defaultValue = "false") String fornitore, @RequestParam(required = true, defaultValue = "false") String emailutente) {
        String message = "";
        try {

        } catch (Exception e) {
            message = "Cannot get report of preventivo: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
        return null;
    }

}
