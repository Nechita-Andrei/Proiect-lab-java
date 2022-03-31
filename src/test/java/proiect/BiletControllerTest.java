package proiect;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import proiect.controller.BiletController;
import proiect.domain.*;
import proiect.repository.*;
import proiect.service.ClientException;

import java.sql.Date;
import java.util.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@Import(BiletController.class)
@WebMvcTest(controllers = BiletController.class)
@EnableTransactionManagement
@ComponentScan(basePackages = {"proiect","proiect.controller"})
public class BiletControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ClientRepo clientRepo;

    @MockBean
    private ZborRepo zborRepo;

    @MockBean
    private DestinatieRepo destinatieRepo;
    @MockBean
    private AvionRepo avionRepo;
    @MockBean
    private AeroportRepo aeroportRepo;
    @MockBean
    private ContRepo contRepo;
    @MockBean
    private PilotRepo pilotRepo;

    private static Optional<Zbor> zbor;
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
    public static List<Zbor> zboruri;

    @BeforeAll
    public static void setup(){
        zboruri=new ArrayList<>();
        avion= Optional.of(new Avion("testAvion",200,2001));
        user_admin=Optional.of(new Client("andrei","andrei2",20, "admin@yahoo.com","0280382","admin",null, Client.Rol.ADMIN));
        user_client=Optional.of(new Client("andrei","andrei3",30, "client@yahoo.com","028038212","client",null, Client.Rol.CLIENT));
        destinatie=Optional.of(new Destinatie("Ungaria","Budapesta", Destinatie.Zona_covid.GALBEN));
        pilot=Optional.of(new Pilot("pilot","test",33,4000, Pilot.Experienta.AVANSAT));
        aeroport_plecare=Optional.of(new Aeroport("henri","Bucuresti",2000));
        aeroport_sosire=Optional.of(new Aeroport("coanda","Bucuresti",1000));
        zbor=Optional.of(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(), Date.valueOf("2021-12-22"),Date.valueOf("2022-01-01"),200));
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
    public void GetDestinatieDataZborTest() throws Exception{
        when(destinatieRepo.findByLocalitate(destinatie.get().getLocalitate())).thenReturn(destinatie);
        when(zborRepo.findAll()).thenReturn(zboruri);
        String endpoint="/bilet/filtrare/{plecare}/{localitate}";
        mockMvc.perform(get(endpoint,"2021-12-22",destinatie.get().getLocalitate())).andExpect(status().isOk());

        when(destinatieRepo.findByLocalitate(destinatie.get().getLocalitate())).thenReturn(Optional.empty());
        mockMvc.perform(get(endpoint,"2021-12-22",destinatie.get().getLocalitate())).andExpect(status().isNotFound());



    }

    @Test
    public void GetZboruriClientTest() throws Exception{
        Set<Zbor> zboruri_client=new HashSet<>();
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(Optional.empty());
        String enpoint="/bilet/zboruri/{client_email}";
        mockMvc.perform(get(enpoint,user_client.get().getEmail())).andExpect(status().isNotFound());
        user_client.get().setZboruri(zboruri_client);
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(user_client);
        mockMvc.perform(get(enpoint,user_client.get().getEmail())).andExpect(status().isNoContent());
        zboruri_client.add(zbor.get());
        user_client.get().setZboruri(zboruri_client);
        mockMvc.perform(get(enpoint,user_client.get().getEmail())).andExpect(status().isOk());

    }
//

    @Test
    public void AddZborClientTest() throws Exception{
        Set<Zbor>zboruri_client=new HashSet<>();
        user_client.get().setZboruri(zboruri_client);
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(user_client);
        when(zborRepo.findById(zbor.get().getId())).thenReturn(zbor);
        when(clientRepo.save(user_client.get())).thenReturn(user_client.get());
        String endpoint="/bilet/zbor/{zbor_id}/client/{client_email}";
        mockMvc.perform(post(endpoint,zbor.get().getId(),user_client.get().getEmail())).andExpect(status().isOk());
        when(zborRepo.findById(zbor.get().getId())).thenReturn(Optional.empty());
        mockMvc.perform(post(endpoint,zbor.get().getId(),user_client.get().getEmail())).andExpect(status().isNotFound());
    }

    @Test
    public void CumparaBiletTest() throws Exception{
        Set<Client>pasageri=new HashSet<>();
        zbor.get().setPasageri(pasageri);
        user_client.get().setCont(cont.get());
        zbor.get().addPasager(user_client.get());
        when(zborRepo.findById(zbor.get().getId())).thenReturn(zbor);
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(user_client);
        String endpoint="/bilet/client/{client_email}/zbor/{zbor_id}";
        mockMvc.perform(post(endpoint,user_client.get().getEmail(),zbor.get().getId())).andExpect(status().isInsufficientStorage()) .andExpect(jsonPath("$").value(ClientException.notEnoughMoney().getErrorCodeClient().toString()));;
        cont.get().setSumaDeBani(10000);
        user_client.get().setCont(cont.get());
        mockMvc.perform(post(endpoint,user_client.get().getEmail(),zbor.get().getId())).andExpect(status().isOk());
    }



}
