package proiect;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import proiect.domain.Destinatie;
import proiect.repository.DestinatieRepo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(value = false)
public class testProfileTest {

    @Autowired
    private DestinatieRepo destinatieRepo;


    @Test
    public void findDestinatie(){
        Destinatie destinatie=destinatieRepo.findByLocalitate("Bucuresti").get();
        assertEquals(destinatie.getZona_covid(), Destinatie.Zona_covid.VERDE);
    }
}
