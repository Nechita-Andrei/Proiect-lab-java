package proiect.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Bilet;
import proiect.domain.BiletId;
import proiect.domain.SumaBileteVanduteAn;

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
}
