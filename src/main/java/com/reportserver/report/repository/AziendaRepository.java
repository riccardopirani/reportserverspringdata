package com.reportserver.report.repository;

import com.reportserver.report.model.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repo Azienda
 */
public interface AziendaRepository extends JpaRepository<Azienda, Integer> {
    /**
     * Caricamento infomazioni Azienda
     *
     * @return
     */
    @Query("FROM Azienda")
    Azienda getInfo();
}
