package com.reportserver.report.repository;


import com.reportserver.report.model.Ristorante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repo ristorante
 */
public interface RistoranteRepository extends JpaRepository<Ristorante, Integer> {
    /**
     * Caricamento Ristoranti dentro un cantiere
     *
     * @param IdCantiere
     * @return
     */
    @Query("FROM Ristorante where IdCantiere=:IdCantiere")
    List<Ristorante> findbycantiere(@Param("IdCantiere") int IdCantiere);
}