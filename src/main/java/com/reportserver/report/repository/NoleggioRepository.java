package com.reportserver.report.repository;

import com.reportserver.report.model.Noleggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repo Noleggio
 */
public interface NoleggioRepository extends JpaRepository<Noleggio, Integer> {

    /**
     * Caricamento noleggi da cantiere
     *
     * @param IdCantiere
     * @return
     */
    @Query("FROM Noleggio where IdCantiere=:IdCantiere")
    List<Noleggio> findbycantiere(@Param("IdCantiere") int IdCantiere);
}