package com.reportserver.report.repository;

import com.reportserver.report.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repo Utente
 */
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    /**
     * Caricamento dati utente
     *
     * @param IdUtente
     * @return
     */
    @Query("FROM Utente where IdUtente=:IdUtente")
    Utente findbyIdUtente(@Param("IdUtente") int IdUtente);
}