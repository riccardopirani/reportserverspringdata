package com.reportserver.report.repository;

import com.reportserver.report.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    //Permette il caricamento dei dati dell'utente
    @Query("FROM Utente where IdUtente=:IdUtente")
    Utente findbyIdUtente(@Param("IdUtente") int IdUtente);
}