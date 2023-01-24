package proiect.repository;

import org.springframework.data.jpa.repository.Query;
import proiect.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.ClientRaport;

import java.util.List;

@Repository
public interface ClientRepo extends CrudRepository<Client,Integer> {
    @Query(value = "select id_client as client, luna ,\n" +
            "        sum(sum(pret)) over (partition by id_client order by luna rows unbounded preceding) cumparat\n" +
            "        from dw.bilet b, dw.timp t where b.id_data_achizitionarii=t.id_timp \n" +
            "        and an=2022\n" +
            "        and id_client=?1\n" +
            "        group by id_client, luna", nativeQuery = true)
    List<ClientRaport> raport3(Integer client_id);


    List<Client> findTop100ByOrderByIdClientDesc();

}
