package com.reportserver.report.repository;

import com.reportserver.report.model.Articolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Repo Aritcolo
 */
public interface ArticoloRepository extends JpaRepository<Articolo, Integer> {

    /**
     * Caricamento lista articoli MDB
     *
     * @param Fornitore
     * @return List<Articolo>
     * @Description caricamento lista articoli MDB
     */
    @Query("FROM Articolo where Fornitore=:Fornitore and Importato='MDB'")
    List<Articolo> findforimportMDB(@Param("Fornitore") String Fornitore);

    /**
     * Caricamento lista articoli comet
     *
     * @return List<Articolo>
     */
    @Query("FROM Articolo where Importato='COMET'")
    List<Articolo> findforimportCOMET();

    /**
     * Questa funzione setta gli articoli MDB come a non disponbili
     *
     * @param Fornitore
     * @return int
     */
    @Transactional
    @Modifying
    @Query("Update Articolo set Stato='Nondisponibile' where Fornitore=:Fornitore and Importato='MDB'")
    int setNonDisponbileMDB(@Param("Fornitore") String Fornitore);

    /**
     * Questa funzione setta gli articoli COMET a non disponbili
     *
     * @return int
     */
    @Transactional
    @Modifying
    @Query("Update Articolo set Stato='Nondisponibile' where  Importato='COMET'")
    int setNonDisponbileCOMET();

    /**
     * Questa funzione setta gli articoli metel di un determinato fornitore come non disponbili
     *
     * @param Fornitore
     * @return int
     */
    @Transactional
    @Modifying
    @Query("Update Articolo set Stato='Nondisponibile' where  Importato='METEL' and Fornitore=:Fornitore")
    int setNonDisponbileMETEL(@Param("Fornitore") String Fornitore);

    /**
     * Conteggio articolo metel di un fornitore e di un CodArt
     *
     * @param Fornitore
     * @param CodArt
     * @return List<Articolo>
     */
    @Query("FROM Articolo where Fornitore=:Fornitore and Importato='METEL' and CodArt=:CodArt")
    List<Articolo> countArticoloMetel(@Param("Fornitore") String Fornitore, @Param("CodArt") String CodArt);

    /**
     * Aggiornamento articolo
     *
     * @param Stato
     * @param Descrizione
     * @param Prezzo
     * @param PrezzoListino
     * @param Fornitore
     * @param CodArt
     * @param Importato
     * @param CodEAN
     * @return int
     */
    @Transactional
    @Modifying
    @Query("Update Articolo set DataAggiornamento=GETDATE(),PrezzoListino=:PrezzoListino,Prezzo=:Prezzo,Descrizione=:Descrizione, Stato=:Stato,CodEAN=:CodEAN where Fornitore=:Fornitore and Importato=:Importato and CodArt=:CodArt ")
    int updateArticolo(@Param("Stato") String Stato, @Param("Descrizione") String Descrizione, @Param("Prezzo") Double Prezzo, @Param("PrezzoListino") Double PrezzoListino, @Param("Fornitore") String Fornitore, @Param("CodArt") String CodArt, @Param("Importato") String Importato, @Param("CodEAN") BigInteger CodEAN);

    /**
     * Calcolo sconto 1 metel
     *
     * @param CodMarca
     * @param Importato
     * @param FamigliaDiSconto
     * @return int
     */
    @Transactional
    @Modifying
    @Query(value = "update Articolo set Prezzo=(PrezzoListino-((PrezzoListino/100) * (select Distinct Sconto1 from Produttore where CodMarca=:CodMarca and FamigliaDiSconto=:FamigliaDiSconto) )) where Importato=:Importato and CodMarca=:CodMarca and FamigliaDiSconto=:FamigliaDiSconto ", nativeQuery = true)
    int updateArticoloMetelScontoFirst(@Param("CodMarca") String CodMarca, @Param("Importato") String Importato, @Param("FamigliaDiSconto") String FamigliaDiSconto);

    /**
     * Calcolo sconto 2 metel
     *
     * @param CodMarca
     * @param Importato
     * @param FamigliaDiSconto
     * @return int
     */
    @Transactional
    @Modifying
    @Query(value = "update Articolo set Prezzo=(Prezzo-((Prezzo/100) * (select Distinct Sconto2 from Produttore where CodMarca=:CodMarca and FamigliaDiSconto=:FamigliaDiSconto) )) where Importato=:Importato and CodMarca=:CodMarca and FamigliaDiSconto=:FamigliaDiSconto ", nativeQuery = true)
    int updateArticoloMetelScontoSecond(@Param("CodMarca") String CodMarca, @Param("Importato") String Importato, @Param("FamigliaDiSconto") String FamigliaDiSconto);

    /**
     * Calcolo sconto 3 metel
     *
     * @param CodMarca
     * @param Importato
     * @param FamigliaDiSconto
     * @return int
     */
    @Transactional
    @Modifying
    @Query(value = "update Articolo set Prezzo=(Prezzo-((Prezzo/100) * (select Distinct Sconto3 from Produttore where CodMarca=:CodMarca and FamigliaDiSconto=:FamigliaDiSconto) )) where Importato=:Importato and CodMarca=:CodMarca and FamigliaDiSconto=:FamigliaDiSconto ", nativeQuery = true)
    int updateArticoloMetelScontoThird(@Param("CodMarca") String CodMarca, @Param("Importato") String Importato, @Param("FamigliaDiSconto") String FamigliaDiSconto);

    /**
     * Inserimento di un nuovo aritcolo
     *
     * @param CodArt
     * @param Prezzo
     * @param Descrizione
     * @param Importato
     * @param Fornitore
     * @param TipoArticolo
     * @param UM
     * @param IdTipologia
     * @param Stato
     * @param CodMarca
     * @param PrezzoListino
     * @param FamigliaDiSconto
     * @param CodEAN
     * @return int
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Articolo (CodArt, Prezzo, Descrizione,Importato,Fornitore,TipoArticolo,UM,IdTipologia,Stato,CodMarca,PrezzoListino,FamigliaDiSconto,CodEAN) VALUES (:CodArt,:Prezzo,:Descrizione,:Importato,:Fornitore,:TipoArticolo,:UM,:IdTipologia,:Stato,:CodMarca,:PrezzoListino,:FamigliaDiSconto,:CodEAN)", nativeQuery = true)
    int insert(@Param("CodArt") String CodArt, @Param("Prezzo") double Prezzo, @Param("Descrizione") String Descrizione, @Param("Importato") String Importato, @Param("Fornitore") String Fornitore, @Param("TipoArticolo") String TipoArticolo, @Param("UM") String UM, @Param("IdTipologia") Integer IdTipologia, @Param("Stato") String Stato, @Param("CodMarca") String CodMarca, @Param("PrezzoListino") double PrezzoListino, @Param("FamigliaDiSconto") String FamigliaDiSconto, @Param("CodEAN") BigInteger CodEAN);
}