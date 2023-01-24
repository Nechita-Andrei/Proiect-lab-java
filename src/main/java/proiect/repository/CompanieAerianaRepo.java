package proiect.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import proiect.domain.Bilet;
import proiect.domain.CompanieAeriana;
import proiect.domain.IntarzieriCompanii;

import java.util.List;

public interface CompanieAerianaRepo extends CrudRepository<CompanieAeriana, Integer> {

    @Query(value = "select companie, rang, an, valoare as intarziere from (\n" +
            "    SELECT  c.denumire as companie, t.an as an,\n" +
            "            SUM(z.intarziere) AS valoare,\n" +
            "            DENSE_RANK() \n" +
            "                    OVER  (ORDER BY SUM(z.intarziere) DESC) AS rang\n" +
            "    FROM   dw.zbor z, dw.timp t, dw.companie_aeriana c\n" +
            "    WHERE  z.id_data_plecare = t.id_timp\n" +
            "    AND    z.id_companie_aeriana = c.id_companie_aeriana\n" +
            "    AND    an = 2023\n" +
            "    GROUP BY  c.denumire, t.an)\n" +
            "where rang<=5", nativeQuery = true)
    List<IntarzieriCompanii> raport5();

    List<CompanieAeriana> findTop100ByOrderByIdCompanieAerianaDesc();
}
