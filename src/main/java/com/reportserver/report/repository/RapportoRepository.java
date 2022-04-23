package com.reportserver.report.repository;

import com.reportserver.report.model.Rapporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repo rapporto
 */
public interface RapportoRepository extends JpaRepository<Rapporto, Integer> {
    /**
     * Caricamento rapporti presenti in un cantiere
     *
     * @param IdCantiere
     * @return
     */
    @Query("FROM Rapporto where IdCantiere=:IdCantiere")
    List<Rapporto> findbycantiere(@Param("IdCantiere") int IdCantiere);
}