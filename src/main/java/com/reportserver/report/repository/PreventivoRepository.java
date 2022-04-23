package com.reportserver.report.repository;

import com.reportserver.report.model.Preventivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repo preventivo
 */
public interface PreventivoRepository extends JpaRepository<Preventivo, Integer> {

    /**
     * Caricamento infomazioni preventivo
     *
     * @param IdPreventivo
     * @return
     */
    @Query("FROM Preventivo where IdPreventivo=:IdPreventivo")
    Preventivo getInfo(@Param("IdPreventivo") int IdPreventivo);
}