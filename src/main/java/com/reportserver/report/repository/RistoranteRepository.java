package com.reportserver.report.repository;

import com.reportserver.report.model.Ristorante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RistoranteRepository extends JpaRepository<Ristorante, Integer> {

    //Permette il caricamento dei ristoranti presenti in un cantiere
    @Query("FROM Ristorante where IdCantiere=:IdCantiere")
    List<Ristorante> findbycantiere(@Param("IdCantiere") int IdCantiere);


}