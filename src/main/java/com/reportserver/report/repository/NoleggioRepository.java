package com.reportserver.report.repository;

import com.reportserver.report.model.Noleggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoleggioRepository extends JpaRepository<Noleggio, Integer> {

    //Permette il caricamento dei rapportini presenti in un cantiere
    @Query("FROM Noleggio where IdCantiere=:IdCantiere")
    List<Noleggio> findbycantiere(@Param("IdCantiere") int IdCantiere);
}