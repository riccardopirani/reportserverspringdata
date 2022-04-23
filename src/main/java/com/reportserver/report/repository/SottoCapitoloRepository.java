package com.reportserver.report.repository;

import com.reportserver.report.model.SottoCapitolo;
import com.reportserver.report.model.TableReportCapitoloPreventivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

/**
 * Repo SottoCapitolo
 */
public interface SottoCapitoloRepository extends JpaRepository<SottoCapitolo, Integer> {
    /**
     * Caricamento valori sotto capitolo
     *
     * @param IdCapitolo
     * @return
     */
    @Query("FROM SottoCapitolo where IdCapitolo=:IdCapitolo")
    List<SottoCapitolo> load(@Param("IdCapitolo") int IdCapitolo);

    /**
     * Caricamento valori stampa unico preventivo
     *
     * @param IdPreventivo
     * @return
     */
    @Transactional
    @Query(nativeQuery = true, value = "DECLARE @T AS table( Descrizione varchar(5000), NumeroCapitolo varchar(100), SottoCapitolo1 varchar(5000), SottoCapitolo2 varchar(5000), SottoCapitolo3 varchar(5000), Totale money, DescrizioneEstesa varchar(5000), Images Image, CostoUnitario money, Quantita money, DescrizioneDis varchar(5000),Tipologia varchar(300),PrezzoRiservato money,UM varchar(50)); INSERT INTO @T SELECT Capitolo.Descrizione, Capitolo.NumeroCapitolo,SottoCapitolo.SottoCapitolo1,SottoCapitolo.SottoCapitolo2,SottoCapitolo.SottoCapitolo3, (ROUND(((((ROUND((((((((SottoCapitolo.CostoUnitario / 100) * PercentualeSpeseGenerali) + SottoCapitolo.CostoUnitario) / 100) * PercentualeUtile) + (((SottoCapitolo.CostoUnitario / 100) * PercentualeSpeseGenerali) + SottoCapitolo.CostoUnitario)) * 1), 2)) / 100) * PercentualeMargine) + (ROUND((((((((SottoCapitolo.CostoUnitario / 100) * PercentualeSpeseGenerali) + SottoCapitolo.CostoUnitario) / 100) * PercentualeUtile) + (((SottoCapitolo.CostoUnitario / 100) * PercentualeSpeseGenerali) + SottoCapitolo.CostoUnitario)) * 1), 2))), 2)) * SottoCapitolo.Quantita as Totale ,SottoCapitolo.DescrizioneEstesa, SottoCapitolo.Images, ROUND(((((ROUND((((((((SottoCapitolo.CostoUnitario / 100) * PercentualeSpeseGenerali) + SottoCapitolo.CostoUnitario) / 100) * PercentualeUtile) + (((SottoCapitolo.CostoUnitario / 100) * PercentualeSpeseGenerali) + SottoCapitolo.CostoUnitario)) * 1), 2)) / 100) * PercentualeMargine) + (ROUND((((((((SottoCapitolo.CostoUnitario / 100) * PercentualeSpeseGenerali) + SottoCapitolo.CostoUnitario) / 100) * PercentualeUtile) + (((SottoCapitolo.CostoUnitario / 100) * PercentualeSpeseGenerali) + SottoCapitolo.CostoUnitario)) * 1), 2))), 2) as CostoUnitario,SottoCapitolo.Quantita  , CASE when Tipologia='Prezzo' then SottoCapitolo.Descrizione else ''+SottoCapitolo.Codice+ '     ' +SottoCapitolo.Descrizione end as DescrizioneDis,Capitolo.Tipologia,Capitolo.PrezzoRiservato,SottoCapitolo.UnitaMisura as UM FROM Capitolo INNER JOIN SottoCapitolo ON SottoCapitolo.IdCapitolo = Capitolo.IdCapitolo  INNER JOIN Preventivo ON Preventivo.IdPreventivo = Capitolo.IdPreventivo  where Preventivo.IdPreventivo =:IdPreventivo ;SELECT CASE WHEN ROW_NUMBER() OVER(PARTITION BY a.Descrizione ORDER BY a.Descrizione) = 1 THEN a.Descrizione ELSE '' END AS Descrizione, Descrizione as Desc2,a.NumeroCapitolo, a.SottoCapitolo1, a.SottoCapitolo2, a.SottoCapitolo3, a.DescrizioneDis, a.CostoUnitario, a.Quantita, a.Totale AS TotaleCap, a.DescrizioneEstesa, a.Images,a.Tipologia,a.PrezzoRiservato,a.UM FROM(SELECT 1 AS d, ROW_NUMBER() OVER(PARTITION BY Descrizione ORDER BY Descrizione) AS r, Descrizione, NumeroCapitolo, SottoCapitolo1, SottoCapitolo2, SottoCapitolo3, Totale, DescrizioneDis, Images, CostoUnitario, Quantita, DescrizioneEstesa,Tipologia,PrezzoRiservato,UM FROM @T  UNION ALL SELECT 2, 0, Descrizione, NumeroCapitolo, '', '', '', SUM(Totale), '', '', '', '','',Tipologia,PrezzoRiservato,'' FROM @T GROUP BY Descrizione,Tipologia,PrezzoRiservato,NumeroCapitolo) AS a ORDER BY a.Descrizione, a.d ")
    Stream<List<TableReportCapitoloPreventivo>> loadValoriPreventivoUnicaPercentuale(@Param("IdPreventivo") int IdPreventivo);
}