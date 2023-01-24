package proiect.repository;

import org.springframework.data.jpa.repository.Query;
import proiect.domain.Pilot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.PilotRaport;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PilotRepo extends CrudRepository<Pilot,Integer> {
    @Query(value = "select pilot, rang, an from (\n" +
            "SELECT  p.nume || ' ' ||p.prenume as pilot, t.an as an,\n" +
            "              SUM(z.durata_zbor)\n" +
            "                  AS valoare,\n" +
            "              DENSE_RANK() \n" +
            "                  OVER  (ORDER BY SUM(z.durata_zbor) DESC) AS rang\n" +
            "       FROM   dw.zbor z, dw.timp t, dw.pilot p\n" +
            "       WHERE  z.id_data_plecare = t.id_timp\n" +
            "       AND    z.id_pilot = p.id_pilot\n" +
            "       AND    an = 2023\n" +
            "       GROUP BY  p.nume, p.prenume, t.an)\n" +
            "       where rang=1", nativeQuery = true)
    PilotRaport raport2();
}
