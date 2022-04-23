package com.reportserver.report.repository;

import com.reportserver.report.model.CapitoloPreventivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CapitoloPreventivoRepository extends JpaRepository<CapitoloPreventivo, Integer> {

    /**
     * Jpa caricamento capitoli preventivi per stampe
     *
     * @param IdPreventivo
     * @return
     */
    @Transactional
    @Query(nativeQuery = true, value = "select *,ABS(CHECKSUM(NEWID()) % 10000) as Id  from (SELECT Codice as CodArt,Descrizione,UM,CostoUnitario as Prezzo,SUM(Quantita) AS Quantita, CAST(ROUND(SUM(Totale), 2) as NUMERIC(12, 2)) AS Totale FROM( /*Articoli */ SELECT SottoCapitolo.Codice, SottoCapitolo.UnitaMisura as UM, SottoCapitolo.Descrizione, SottoCapitolo.CostoUnitario, SUM(SottoCapitolo.Quantita) AS Quantita, SUM(SottoCapitolo.CostoUnitario * SottoCapitolo.Quantita) as Totale FROM SottoCapitolo INNER JOIN Capitolo ON Capitolo.IdCapitolo = SottoCapitolo.IdCapitolo inner join Preventivo on Preventivo.IdPreventivo = Capitolo.IdPreventivo WHERE SottoCapitolo.Tipo = 'Articolo' and Preventivo.IdPreventivo =:IdPreventivo  GROUP BY SottoCapitolo.Codice, SottoCapitolo.UnitaMisura, SottoCapitolo.Descrizione, SottoCapitolo.CostoUnitario UNION ALL  /* Disitnte -- con articoli diversi da manodopera*/  SELECT Articolo.CodArt as Codice, Articolo.UM, Articolo.Descrizione, Articolo.Prezzo, SUM(SottoCapitolo.Quantita * (DistintaSemplice.Quantita)) as Quantita, cast(ROUND((Articolo.Prezzo * (SUM(DistintaSemplice.Quantita * SottoCapitolo.Quantita))), 2) as numeric(12, 2)) as Totale FROM Preventivo INNER JOIN Capitolo ON Capitolo.IdPreventivo = Preventivo.IdPreventivo INNER JOIN SottoCapitolo ON SottoCapitolo.IdCapitolo = Capitolo.IdCapitolo INNER JOIN Distinta ON SottoCapitolo.CodiceDistinta = Distinta.CodiceDistinta INNER JOIN DistintaSemplice ON DistintaSemplice.CodiceDistinta = Distinta.CodiceDistinta INNER JOIN Articolo ON Articolo.IdArticolo = DistintaSemplice.IdArticolo WHERE Articolo.TipoArticolo != 'M' and SottoCapitolo.Tipo = 'Distinta' and Preventivo.IdPreventivo =:IdPreventivo  GROUP BY  Articolo.CodArt, Articolo.UM, Articolo.Descrizione, Articolo.Prezzo UNION ALL Select Articolo.CodArt, Articolo.UM, Articolo.Descrizione, Articolo.Prezzo, (/*ore*/CAST(CAST(ROUND(((SottoDistinta.Quantita * SottoCapitolo.Quantita) + SottoDistinta.Quantita) / (DistintaSemplice.Divisore), 2) as int) as decimal) + /*minuti*/ CAST(((((PARSE('0,' + PARSENAME(ROUND((((SottoDistinta.Quantita * SottoCapitolo.Quantita) + SottoDistinta.Quantita) / (DistintaSemplice.Divisore)), 2), 1) as money)) / 100) / 60) * 100) as money)) as Quantita, (((/*ore*/CAST(CAST(ROUND(((SottoDistinta.Quantita * SottoCapitolo.Quantita) + SottoDistinta.Quantita) / (DistintaSemplice.Divisore), 2) as int) as decimal) + /*minuti*/ CAST(((((PARSE('0,' + PARSENAME(ROUND((((SottoDistinta.Quantita * SottoCapitolo.Quantita) + SottoDistinta.Quantita) / (DistintaSemplice.Divisore)), 2), 1) as money)) / 100) / 60) * 100) as money))) * Articolo.Prezzo) as Totale from SottoCapitolo inner join Distinta on Distinta.CodiceDistinta = SottoCapitolo.CodiceDistinta inner join SottoDistinta on SottoDistinta.CodiceDistinta = SottoCapitolo.CodiceDistinta inner join DistintaSemplice on DistintaSemplice.CodiceDistinta = SottoDistinta.CodiceSottoDistinta inner join Articolo on Articolo.IdArticolo = DistintaSemplice.IdArticolo inner join Capitolo on Capitolo.IdCapitolo = SottoCapitolo.IdCapitolo inner join Preventivo on Preventivo.IdPreventivo = Capitolo.IdPreventivo where SottoCapitolo.Tipo = 'Distinta' and Preventivo.IdPreventivo =:IdPreventivo and Articolo.TipoArticolo!='M' )Q GROUP BY Codice, UM, Descrizione, CostoUnitario )q")
    List<CapitoloPreventivo> load(@Param("IdPreventivo") int IdPreventivo);
}
