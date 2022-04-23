package com.reportserver.report.repository;

import com.reportserver.report.model.Kilometri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repo Kilometri
 */
public interface KilometriRepository extends JpaRepository<Kilometri, Integer> {
    /**
     * Caricamento kilometri da cantiere
     *
     * @param IdCantiere
     * @return
     */
    @Query("FROM Kilometri where IdCantiere=:IdCantiere")
    List<Kilometri> findbycantiere(@Param("IdCantiere") int IdCantiere);
}