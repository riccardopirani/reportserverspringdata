package com.reportserver.report.repository;

import com.reportserver.report.model.SpeseSostenute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpeseSostenuteRepository extends JpaRepository<SpeseSostenute, Integer> {
    //Permette il caricamento della lista delle spese presenti in un cantiere
    @Query("FROM SpeseSostenute where IdCantiere=:IdCantiere")
    List<SpeseSostenute> findbycantiere(@Param("IdCantiere") int IdCantiere);

}