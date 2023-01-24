package proiect.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.*;

import java.util.List;

@Repository
public interface BiletRepo extends CrudRepository<Bilet,BiletId> {
    @Query(value = "select NVL(max(valoare_cumulata),0) as suma from(\n" +
            "SELECT id_timp, \n" +
            "       valoare,\n" +
            "       SUM(valoare) OVER  \n" +
            "          (ORDER BY id_timp\n" +
            "          ROWS UNBOUNDED PRECEDING) \n" +
            "          AS valoare_cumulata\n" +
            "FROM  (SELECT t.id_timp, SUM(v.pret) valoare\n" +
            "       FROM   dw.bilet v, dw.timp t\n" +
            "       WHERE  v.id_data_achizitionarii=t.id_timp\n" +
            "       AND    t.an = 2022\n" +
            "       GROUP BY t.id_timp))", nativeQuery = true)
    SumaBileteVanduteAn raport1();



    @Query(value = "SELECT id_timp as timp, zi||' '||luna||' '||an as data, vanzari,\n" +
            "       nvl(vanzari-LAG(vanzari, 1) OVER ( ORDER BY id_timp),0)\n" +
            "         AS Diferenta\n" +
            "FROM (SELECT zi, luna, an, id_timp, \n" +
            "             SUM(pret) AS vanzari\n" +
            "      FROM   dw.bilet v, dw.timp t\n" +
            "      WHERE  v.id_data_achizitionarii=t.id_timp\n" +
            "      AND    luna= 11\n" +
            "      AND    an = 2022\n" +
            "      GROUP BY zi,luna,an, id_timp)", nativeQuery = true)
    List<EvolutieRaport> raport4();

    List<Bilet> findTop100ByOrderByPretDesc();
}
