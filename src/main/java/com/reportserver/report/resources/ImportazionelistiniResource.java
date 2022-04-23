package com.reportserver.report.resources;

import com.reportserver.report.enums.ListiniType;
import com.reportserver.report.model.ResponseMessage;
import com.reportserver.report.services.ArticoloService;
import com.reportserver.report.services.FileStorage;
import com.reportserver.report.utils.Support;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Resource utilizzato per l'importazione dei listini
 */
@Description(value = "Importazione Listini")
@RestController
@RequestMapping("/api/importazione")
public class ImportazionelistiniResource {

    public static Boolean isWaiting = false;
    @Autowired
    FileStorage storageService;
    @Autowired
    ArticoloService articoloService;

    /**
     * Endpoint upload file
     *
     * @return byte array resource (generated file in bytes)
     */
    @PostMapping("/upload/mdb")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(required = true, defaultValue = "false") String fornitore, @RequestParam(required = true, defaultValue = "false") String emailutente) {
        String message = "";
        try {
            //Eseguo la rimozione del file se presente
            if (Support.deleteLocalFile(file)) {
                //Salvo il file nel filesystem
                storageService.save(file);
                //Avvio l'import del file
                articoloService.importalistino(fornitore, file, ListiniType.MDB, null, emailutente, 0, 0);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } else {
                message = "Error delete local file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /**
     * Endpoint upload file comet
     *
     * @return byte array resource (generated file in bytes)
     */
    @PostMapping("/upload/comet")
    public ResponseEntity<ResponseMessage> uploadFileComet(@RequestParam("file") MultipartFile file, @RequestParam(required = true, defaultValue = "false") String password, @RequestParam(required = true, defaultValue = "false") String emailutente) {
        String message = "";
        try {
            //Eseguo la rimozione del file se presente
            if (Support.deleteLocalFile(file)) {
                //Salvo il file nel filesystem
                storageService.save(file);
                //Avvio l'importazione del listino
                articoloService.importalistino("", file, ListiniType.COMET, password, emailutente, 0, 0);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } else {
                message = "Error delete local file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /**
     * Endpoint upload file metel
     *
     * @return byte array resource (generated file in bytes)
     */
    @PostMapping("/upload/metel")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseMessage> uploadFileMeltel(@RequestParam("file") MultipartFile file, @RequestParam(required = true, defaultValue = "false") String password, @RequestParam(required = true, defaultValue = "false") String emailutente, @RequestParam(required = true) int IdFornitore, @RequestParam(required = true) int IdUtente) {
        String message = "";
        try {
            Logger logger = LogManager.getRootLogger();
            logger.info("Avvio importazione METEL");
            Support.deleteLocalFile(file);
            isWaiting = true;
            //Salvo il file nel filesystem
            storageService.save(file);
            articoloService.importalistino("", file, ListiniType.METEL, password, emailutente, IdFornitore, IdUtente);
            isWaiting = false;
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception ex) {
            message = "Exception: " + ex;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /**
     * Update price of Produttore
     *
     * @return byte array resource (generated file in bytes)
     */
    @PostMapping("/update/price")
    public ResponseEntity<ResponseMessage> update(@RequestParam(required = true) String FamigliaDiSconto, @RequestParam(required = true) int IdFornitore, @RequestParam(required = true) Double Sconto1, @RequestParam(required = true) Double Sconto2, @RequestParam(required = true) Double Sconto3) {
        String message = "";
        try {
            Boolean ret = articoloService.aggiornascontoproduttore(Sconto1, Sconto2, Sconto3, IdFornitore, FamigliaDiSconto);
            message = " successfully: " + ret;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /**
     * Get status importazione listini
     *
     * @return boolean
     */
    @GetMapping("/status")
    public ResponseEntity<ResponseMessage> getstatus() {
        String message = "";
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(articoloService.checkImportazione.toString()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
