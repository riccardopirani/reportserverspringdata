package com.reportserver.report.repository;

import com.reportserver.report.model.SpeseSostenute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repo SpeseSostenute
 */
public interface SpeseSostenuteRepository extends JpaRepository<SpeseSostenute, Integer> {
    /**
     * Caricamento spese presenti in un cantiere
     *
     * @param IdCantiere
     * @return
     */
    @Query("FROM SpeseSostenute where IdCantiere=:IdCantiere")
    List<SpeseSostenute> findbycantiere(@Param("IdCantiere") int IdCantiere);
}