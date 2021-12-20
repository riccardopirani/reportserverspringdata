package com.reportserver.report.repository;

import com.reportserver.report.model.Kilometri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KilometriRepository extends JpaRepository<Kilometri, Integer> {

    //All the kilometri present on site are loaded
    @Query("FROM Kilometri where IdCantiere=:IdCantiere")
    List<Kilometri> findbycantiere(@Param("IdCantiere") int IdCantiere);
}