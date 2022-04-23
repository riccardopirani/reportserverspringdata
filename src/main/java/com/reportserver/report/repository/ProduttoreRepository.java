package com.reportserver.report.repository;

import com.reportserver.report.model.Produttore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repo produttore
 */
public interface ProduttoreRepository extends JpaRepository<Produttore, Integer> {

    /**
     * Conta famiglie di sconto
     *
     * @param FamigliaDiSconto
     * @param CodMarca
     * @param IdFornitore
     * @return
     */
    @Query(value = "Select ISNULL(COUNT(*),0) FROM Produttore where FamigliaDiSconto=:FamigliaDiSconto and CodMarca=:CodMarca and IdFornitore=:IdFornitore", nativeQuery = true)
    int contafamigliedisconto(@Param("FamigliaDiSconto") String FamigliaDiSconto, @Param("CodMarca") String CodMarca, @Param("IdFornitore") int IdFornitore);

    /**
     * Inserimento produttore
     *
     * @param IdFornitore
     * @param FamigliaDiSconto
     * @param Sconto1
     * @param Sconto2
     * @param Sconto3
     * @param CodMarca
     * @param IdUtente
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Produttore (IdFornitore, FamigliaDiSconto, Sconto1,Sconto2,Sconto3,CodMarca,IdUtente) VALUES (:IdFornitore, :FamigliaDiSconto, :Sconto1,:Sconto2,:Sconto3,:CodMarca,:IdUtente)", nativeQuery = true)
    int insert(@Param("IdFornitore") int IdFornitore, @Param("FamigliaDiSconto") String FamigliaDiSconto, @Param("Sconto1") double Sconto1, @Param("Sconto2") double Sconto2, @Param("Sconto3") double Sconto3, @Param("CodMarca") String CodMarca, @Param("IdUtente") int IdUtente);

    /**
     * Recupero lista produttori per idFornitore
     *
     * @param IdFornitore
     * @return
     */
    @Query("FROM Produttore where IdFornitore=:IdFornitore")
    List<Produttore> get(@Param("IdFornitore") int IdFornitore);

    /**
     * Aggiornamento articolo produttore
     *
     * @param Sconto1
     * @param Sconto2
     * @param Sconto3
     * @param IdFornitore
     * @param FamigliaDiSconto
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "Update Produttore set Sconto1=:Sconto1,Sconto2=:Sconto2,Sconto3=:Sconto3 where IdFornitore=:IdFornitore and FamigliaDiSconto=:FamigliaDiSconto", nativeQuery = true)
    int update(@Param("Sconto1") double Sconto1, @Param("Sconto2") Double Sconto2, @Param("Sconto3") double Sconto3, @Param("IdFornitore") int IdFornitore, @Param("FamigliaDiSconto") String FamigliaDiSconto);

    /**
     * Aggiornamento aritcolo fornitore
     *
     * @param Sconto1
     * @param Sconto2
     * @param Sconto3
     * @param IdFornitore
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "Update Produttore set Sconto1=:Sconto1,Sconto2=:Sconto2,Sconto3=:Sconto3 where IdFornitore=:IdFornitore ", nativeQuery = true)
    int updateByFornitore(@Param("Sconto1") double Sconto1, @Param("Sconto2") Double Sconto2, @Param("Sconto3") double Sconto3, @Param("IdFornitore") int IdFornitore);
}