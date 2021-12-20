package com.reportserver.report.repository;

import com.reportserver.report.model.Noteimpianto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteimpiantoRepository extends JpaRepository<Noteimpianto, Integer> {

    //Carico le note presenti nell'impianto
    @Query("FROM Noteimpianto where IdImpianto=:IdImpianto")
    List<Noteimpianto> findbyIdImpianto(@Param("IdImpianto") int IdImpianto);
}