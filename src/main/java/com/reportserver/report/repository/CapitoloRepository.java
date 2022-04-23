package com.reportserver.report.repository;

import com.reportserver.report.model.Capitolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repo capitolo
 */
public interface CapitoloRepository extends JpaRepository<Capitolo, Integer> {
    /**
     * Caricamento capitoli preventivo
     *
     * @param IdPreventivo
     * @return
     */
    @Query("FROM Capitolo where IdPreventivo=:IdPreventivo")
    List<Capitolo> loadByIdPreventivo(@Param("IdPreventivo") int IdPreventivo);
}
