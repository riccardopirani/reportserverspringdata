package com.reportserver.report.repository;

import com.reportserver.report.model.RisorseUmane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisorseUmaneRepository extends JpaRepository<RisorseUmane, Integer> {

    //All the risorse umane present on site are loaded
    @Query("FROM RisorseUmane where IdCantiere=:IdCantiere")
    List<RisorseUmane> findbycantiere(@Param("IdCantiere") int IdCantiere);

    //Calcolo totale ore
    @Query(value = " Select  ((REPLACE((left(CAST(REPLACE((convert(varchar(5), Cast(convert(varchar(5), (RisorseUmane.OreFine - RisorseUmane.OreInizio), 108) as datetime) - CAST(REPLACE(RisorseUmane.Pausa, '.', ':') as datetime), 108)),':',',')AS money)/100, 2) ),'.',''))+(((CAST('0,'+(RIGHT(CAST(REPLACE((convert(varchar(5), Cast(convert(varchar(5), (RisorseUmane.OreFine - RisorseUmane.OreInizio), 108) as datetime) - CAST(REPLACE(RisorseUmane.Pausa, '.', ':') as datetime), 108)),':',',')AS money)/100, 2) ) as money)/100)*100)/60)) as Quantita from RisorseUmane inner join Utente  on RisorseUmane.IdUtente = Utente.IdUtente  where RisorseUmane.IdCantiere=:IdCantiere and RisorseUmane.IdUtente=:IdUtente", nativeQuery = true)
    String gettotaleore(@Param("IdCantiere") int IdCantiere, @Param("IdUtente") int IdUtente);
}