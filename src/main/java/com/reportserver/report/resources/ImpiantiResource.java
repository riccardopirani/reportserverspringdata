package com.reportserver.report.resources;

import com.reportserver.report.model.Impianto;
import com.reportserver.report.services.NoteService;
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
@RequestMapping("/api/report/impianti")
public class ImpiantiResource {

    /**
     * Parameter of note impianto
     *
     * @param servicenote - note impianto service dependency
     */
    @Autowired
    private NoteService servicenote;

    /**
     * Endpoint that print note impianto
     *
     * @return byte array resource (generated file in bytes)
     */
    @PostMapping(value = "/note", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> print(HttpServletResponse response, @Valid @RequestBody Impianto imp) throws JSONException, IOException {
        try {
            Boolean check;
            check = servicenote.generateReport(imp);
            return new ResponseEntity<>(check, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid Ricarico", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint return report file pdf of note
     *
     * @return Pdf file of report note
     */
    @GetMapping(value = "/getfile.pdf")
    public ResponseEntity<ByteArrayResource> download(String param) throws IOException {
        File file;
        file = new File("/var/tmp/note.pdf");
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