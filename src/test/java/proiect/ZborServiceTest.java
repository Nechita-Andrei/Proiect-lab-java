package proiect;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiect.domain.*;
import proiect.repository.*;
import proiect.service.ClientException;
import proiect.service.ZborException;
import proiect.service.ZborService;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ZborServiceTest {

    @InjectMocks
    private ZborService zborService;



    @Mock
    private AeroportRepo aeroportRepo;

    @Mock
    private AvionRepo avionRepo;

    @Mock
    private PilotRepo pilotRepo;

    @Mock
    private DestinatieRepo destinatieRepo;

    @Mock
    private ClientRepo clientRepo;
    private static Optional<Adresa>adresa1;
    private static Optional<Adresa>adresa2;
    private static Optional<Avion>avion;
    private static Optional<Client> user_client;
    private static Optional<Client> user_admin;
    private static Optional<Destinatie> destinatie;
    private static Optional<Pilot> pilot;
    private static Optional<Aeroport> aeroport_plecare;
    private static Optional<Aeroport> aeroport_sosire;

    @BeforeAll
    public static void setup(){
        adresa1=Optional.of(new Adresa("Bucuresti","Bucuresti","1 mai"));
        adresa2=Optional.of(new Adresa("Bucuresti","Bucuresti","2 mai"));
        avion= Optional.of(new Avion("testAvion",200,2001));
        user_admin=Optional.of(new Client("andrei","andrei2",20, "admin@yahoo.com","0280382","admin",null, Client.Rol.ADMIN,adresa1.get()));
        user_client=Optional.of(new Client("andrei","andrei3",30, "client@yahoo.com","028038212","client",null, Client.Rol.CLIENT,adresa2.get()));
        destinatie=Optional.of(new Destinatie("Ungaria","Budapesta", Destinatie.Zona_covid.GALBEN));
        pilot=Optional.of(new Pilot("pilot","test",33,4000, Pilot.Experienta.AVANSAT));
        aeroport_plecare=Optional.of(new Aeroport("henri","Bucuresti",2000));
        aeroport_sosire=Optional.of(new Aeroport("coanda","Bucuresti",1000));

    }

    @Test
    public void testAddAvion(){
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(user_client);
        ClientException clientException=assertThrows(ClientException.class, ()->zborService.addAvion(avion.get().getModel(),avion.get().getNr_locuri(),avion.get().getAn_constructie(),user_client.get().getEmail(),user_client.get().getParola()));
        assertEquals(ClientException.ErrorCodeClient.DOES_NOT_HAVE_PERMISSION,clientException.getErrorCodeClient());
    }
    //pt aeroport, destinatie si pilot testele sunt asemanatoare

    @Test
    public void testAddZbor(){
        when(clientRepo.findByEmail(user_admin.get().getEmail())).thenReturn(user_admin);
        when(aeroportRepo.findById(aeroport_plecare.get().getId())).thenReturn(aeroport_plecare);
        when(aeroportRepo.findById(aeroport_sosire.get().getId())).thenReturn(aeroport_sosire);
        when(avionRepo.findById(avion.get().getId())).thenReturn(Optional.empty());
        when(pilotRepo.findById(pilot.get().getId())).thenReturn(pilot);
        lenient().when(destinatieRepo.findById(destinatie.get().getId())).thenReturn(destinatie);
        ZborException zborException=assertThrows(ZborException.class,()->zborService.addZbor(aeroport_plecare.get().getId(),aeroport_sosire.get().getId(),pilot.get().getId(),
                avion.get().getId(),destinatie.get().getId(), Date.valueOf("2021-12-22"),Date.valueOf("2022-01-01"),100,user_admin.get().getEmail(),user_admin.get().getParola()));
        assertEquals(ZborException.ErrorCodeZbor.AVIONUL_NU_A_FOST_GASIT,zborException.getErrorCodeZbor());

    }

    //pe restul cazurilor testele sunt asemanatiare
}
