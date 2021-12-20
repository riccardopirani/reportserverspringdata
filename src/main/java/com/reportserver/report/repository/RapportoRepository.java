package com.reportserver.report.repository;

import com.reportserver.report.model.Rapporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RapportoRepository extends JpaRepository<Rapporto, Integer> {

    //Permette il caricamento dei rapportini presenti in un cantiere
    @Query("FROM Rapporto where IdCantiere=:IdCantiere")
    List<Rapporto> findbycantiere(@Param("IdCantiere") int IdCantiere);
}