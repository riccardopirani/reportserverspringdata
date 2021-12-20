package com.reportserver.report.repository;

import com.reportserver.report.model.ArticoloCantiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticoloCantiereRepository extends JpaRepository<ArticoloCantiere, Integer> {

    //All the articles present on site are loaded
    @Query("FROM ArticoloCantiere where IdCantiere=:IdCantiere")
    List<ArticoloCantiere> findbycantiere(@Param("IdCantiere") int IdCantiere);
}