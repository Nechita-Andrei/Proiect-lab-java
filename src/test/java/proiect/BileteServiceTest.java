package proiect;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiect.domain.*;
import proiect.repository.ClientRepo;
import proiect.repository.DestinatieRepo;
import proiect.repository.ZborRepo;
import proiect.service.BiletService;
import proiect.service.ClientException;
import proiect.service.ZborException;

import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BileteServiceTest {

    @InjectMocks
    private BiletService biletService;
    @Mock
    private ClientRepo clientRepo;
    @Mock
    private ZborRepo zborRepo;
    @Mock
    private DestinatieRepo destinatieRepo;
    private static Optional<Adresa>adresa1;
    private static Optional<Adresa>adresa2;
    private static Optional<Zbor>zbor;
    private static Optional<Zbor>zbor2;
    private static Optional<Zbor>zbor3;
    private static Optional<Zbor>zbor4;
    private static Optional<Zbor>zbor5;
    private static Optional<Zbor>zbor6;
    private static Optional<Zbor>zbor7;
    private static Optional<Zbor>zbor8;
    private static Optional<Zbor>zbor9;
    private static Optional<Zbor>zbor10;
    private static Optional<Avion>avion;
    private static Optional<Client> user_client;
    private static Optional<Client> user_admin;
    private static Optional<Cont> cont;
    private static Optional<Destinatie> destinatie;
    private static Optional<Pilot> pilot;
    private static Optional<Aeroport> aeroport_plecare;
    private static Optional<Aeroport> aeroport_sosire;
    public static List<Zbor>zboruri;

    @BeforeAll
    public static void setup(){
        zboruri=new ArrayList<>();
        adresa1=Optional.of(new Adresa("Bucuresti","Bucuresti","1 mai"));
        adresa2=Optional.of(new Adresa("Bucuresti","Bucuresti","2 mai"));
        avion= Optional.of(new Avion("testAvion",200,2001));
        user_admin=Optional.of(new Client("andrei","andrei2",20, "admin@yahoo.com","0280382","admin",null, Client.Rol.ADMIN,adresa1.get()));
        user_client=Optional.of(new Client("andrei","andrei3",30, "client@yahoo.com","028038212","client",null, Client.Rol.CLIENT,adresa2.get()));
        destinatie=Optional.of(new Destinatie("Ungaria","Budapesta", Destinatie.Zona_covid.GALBEN));
        pilot=Optional.of(new Pilot("pilot","test",33,4000, Pilot.Experienta.AVANSAT));
        aeroport_plecare=Optional.of(new Aeroport("henri","Bucuresti",2000));
        aeroport_sosire=Optional.of(new Aeroport("coanda","Bucuresti",1000));
        zbor=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-01-01"),200));
        zbor2=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-02-01"),200));
        zbor3=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-03-01"),200));
        zbor4=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-04-01"),200));
        zbor5=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-05-01"),200));
        zbor6=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-06-01"),200));
        zbor7=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-07-01"),200));
        zbor8=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-08-01"),200));
        zbor9=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-09-01"),200));
        zbor10=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),Date.valueOf("2021-12-22"),Date.valueOf("2022-10-01"),200));
        cont=Optional.of(new Cont("BCR",10,"123"));
        zboruri.add(zbor.get());
    }

    @Test
    public void GetPasageriTest(){
        when(zborRepo.findById(zbor.get().getId())).thenReturn(Optional.empty());
        ZborException zborException=assertThrows(ZborException.class, ()->biletService.getPasageri(zbor.get().getId()));
        assertEquals(ZborException.ErrorCodeZbor.ZBORUL_NU_A_FOST_GASIT,zborException.getErrorCodeZbor());
    }

    @Test
    public void GetDestinatieDataZborTest(){
        List<Zbor> rezultat;
        when(destinatieRepo.findByLocalitate(destinatie.get().getLocalitate())).thenReturn(destinatie);
        when(zborRepo.findAll()).thenReturn(zboruri);
        rezultat=biletService.getZboruriDataDestinatie(Date.valueOf("2021-12-22"),destinatie.get().getLocalitate());
        assertEquals(rezultat,zboruri);

    }

    @Test
    public void GetZboruriClientTest(){
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(Optional.empty());
        ClientException clientException=assertThrows(ClientException.class, ()->biletService.getZboruriClient(user_client.get().getEmail()));
        assertEquals(ClientException.ErrorCodeClient.CLIENT_NOT_FOUND,clientException.getErrorCodeClient());
    }

    @Test
    public void VerificaDiscountTest(){
        Set<Zbor>zboruri_client=new HashSet<>();
        user_client.get().setZboruri(zboruri_client);
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(user_client);
        assertFalse(user_client.get().isDiscount());
        zboruri_client.add(zbor.get());
        zboruri_client.add(zbor2.get());
        zboruri_client.add(zbor3.get());
        zboruri_client.add(zbor4.get());
        zboruri_client.add(zbor5.get());
        zboruri_client.add(zbor6.get());
        zboruri_client.add(zbor7.get());
        zboruri_client.add(zbor8.get());
        zboruri_client.add(zbor9.get());
        zboruri_client.add(zbor10.get());
        user_client.get().setZboruri(zboruri_client);
        biletService.verificaDiscount(user_client.get().getEmail());
        assertTrue(user_client.get().isDiscount());
    }

    @Test
    public void AddZborClientTest(){
        Set<Zbor>zboruri_client=new HashSet<>();
        user_client.get().setZboruri(zboruri_client);
        assertEquals(0, user_client.get().getZboruri().size());
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(user_client);
        when(zborRepo.findById(zbor.get().getId())).thenReturn(zbor);
        when(clientRepo.save(user_client.get())).thenReturn(user_client.get());
        biletService.addZborClient(zbor.get().getId(),user_client.get().getEmail());
        assertEquals(1, user_client.get().getZboruri().size());
    }

    @Test
    public void CumparaBiletTest(){
        user_client.get().setCont(cont.get());
        when(zborRepo.findById(zbor.get().getId())).thenReturn(zbor);
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(user_client);
        ClientException clientException=assertThrows(ClientException.class, ()->biletService.cumparaBilet(zbor.get().getId(),user_client.get().getEmail()));
        assertEquals(ClientException.ErrorCodeClient.NOT_ENOUGH_MONEY,clientException.getErrorCodeClient());
    }
}
