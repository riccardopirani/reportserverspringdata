package com.reportserver.report.repository;

import com.reportserver.report.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repo cliente
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    /**
     * Caricamento infomazioni cliente
     *
     * @param IdCliente
     * @return
     */
    @Query("FROM Cliente where IdCliente=:IdCliente")
    Cliente findbyIdCliente(@Param("IdCliente") int IdCliente);
}