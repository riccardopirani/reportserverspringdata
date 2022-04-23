package com.reportserver.report.repository;

import com.reportserver.report.model.Noteimpianto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repo note
 */
public interface NoteimpiantoRepository extends JpaRepository<Noteimpianto, Integer> {

    /**
     * Caricamento note impianto
     *
     * @param IdImpianto
     * @return
     */
    @Query("FROM Noteimpianto where IdImpianto=:IdImpianto")
    List<Noteimpianto> findbyIdImpianto(@Param("IdImpianto") int IdImpianto);
}