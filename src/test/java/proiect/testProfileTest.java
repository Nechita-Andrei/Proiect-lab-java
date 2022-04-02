package proiect;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import proiect.domain.Avion;
import proiect.domain.Destinatie;
import proiect.repository.AvionRepo;
import proiect.repository.DestinatieRepo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@Rollback(value = false)
public class testProfileTest {

    @Autowired
    private DestinatieRepo destinatieRepo;

    @Autowired
    private AvionRepo avionRepo;


    @Test
    public void testFindDestinatie(){
        Destinatie destinatie=destinatieRepo.findByLocalitate("Bucuresti").get();
        assertEquals(destinatie.getZona_covid(), Destinatie.Zona_covid.VERDE);
    }

    @Test
    public void testAdaugareAvion(){
        Avion avion=new Avion("avion de test",200,2010);
        Avion avionCreat=avionRepo.save(avion);
        assertNotNull(avionCreat.getId());
        assertEquals(avion.getAn_constructie(),avionCreat.getAn_constructie());
        assertEquals(avion.getModel(),avionCreat.getModel());
    }
}
