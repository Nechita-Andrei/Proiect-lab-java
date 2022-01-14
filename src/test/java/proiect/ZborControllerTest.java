package proiect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import proiect.controller.ZborController;
import proiect.domain.*;
import proiect.repository.*;
import proiect.service.ClientException;
import proiect.service.ZborException;

import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = ZborController.class)
@EnableTransactionManagement
@ComponentScan
public class ZborControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

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

    private static List<Zbor> zboruri;
    private static Optional<Avion> avion;
    private static Optional<Client> user_client;
    private static Optional<Client> user_admin;
    private static Optional<Destinatie> destinatie;
    private static Optional<Pilot> pilot;
    private static Optional<Aeroport> aeroport_plecare;
    private static Optional<Aeroport> aeroport_sosire;

    @BeforeAll
    public static void setup(){
        avion= Optional.of(new Avion("testAvion",200,2001));
        user_admin=Optional.of(new Client("andrei","andrei2",20, "admin@yahoo.com","0280382","admin",null, Client.Rol.ADMIN));
        user_client=Optional.of(new Client("andrei","andrei3",30, "client@yahoo.com","028038212","client",null, Client.Rol.CLIENT));
        destinatie=Optional.of(new Destinatie("Ungaria","Budapesta", Destinatie.Zona_covid.GALBEN));
        pilot=Optional.of(new Pilot("pilot","test",33,4000, Pilot.Experienta.AVANSAT));
        aeroport_plecare=Optional.of(new Aeroport("henri","Bucuresti",2000));
        aeroport_sosire=Optional.of(new Aeroport("coanda","Bucuresti",1000));
        zboruri=new ArrayList<>();
        zboruri.add(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(), Date.valueOf("2021-12-22"),Date.valueOf("2022-01-01"),250));

    }

    @Test
    public void testGetZboruri()throws Exception{
        when(zborRepo.findAll()).thenReturn(Collections.emptyList());
        String endpoint="/zbor";
        mockMvc.perform(get(endpoint)).andExpect(status().isNoContent());
        when(zborRepo.findAll()).thenReturn(zboruri);
        mockMvc.perform(get(endpoint)).andExpect(status().isOk());
    }

    @Test
    public void testAddAvion()throws Exception{
        when(clientRepo.findByEmail(user_client.get().getEmail())).thenReturn(user_client);
        String endpoint="/zbor/avion";
        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED).param("model",avion.get().getModel()).param("numar_locuri",String.valueOf(avion.get().getNr_locuri()))
                .param("an_constructie",String.valueOf(avion.get().getAn_constructie())).param("user",user_client.get().getEmail()).param("parola",user_client.get().getParola())).andExpect(status().isForbidden())
                .andExpect(jsonPath("$").value(ClientException.noPermission().getErrorCodeClient().toString()));

        when(clientRepo.findByEmail(user_admin.get().getEmail())).thenReturn(Optional.empty());
        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED).param("model",avion.get().getModel()).param("numar_locuri",String.valueOf(avion.get().getNr_locuri()))
                        .param("an_constructie",String.valueOf(avion.get().getAn_constructie())).param("user",user_admin.get().getEmail()).param("parola",user_admin.get().getParola())).andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").value(ClientException.clientNotFound().getErrorCodeClient().toString()));

        when(clientRepo.findByEmail(user_admin.get().getEmail())).thenReturn(user_admin);
        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED).param("model",avion.get().getModel()).param("numar_locuri",String.valueOf(avion.get().getNr_locuri()))
                        .param("an_constructie",String.valueOf(avion.get().getAn_constructie())).param("user",user_admin.get().getEmail()).param("parola",user_admin.get().getParola())).andExpect(status().isOk());
    }
    //testele sunt asemanatoare pentru adaugare-aeroport/destinatie/pilot

    @Test
    public void testAddZbor() throws Exception{
        when(clientRepo.findByEmail(user_admin.get().getEmail())).thenReturn(user_admin);
        when(aeroportRepo.findById(aeroport_plecare.get().getId())).thenReturn(aeroport_plecare);
        when(aeroportRepo.findById(aeroport_sosire.get().getId())).thenReturn(aeroport_sosire);
        when(avionRepo.findById(avion.get().getId())).thenReturn(Optional.empty());
        when(pilotRepo.findById(pilot.get().getId())).thenReturn(pilot);
        lenient().when(destinatieRepo.findById(destinatie.get().getId())).thenReturn(destinatie);
        String endpoint="/zbor";
        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED).param("aeroport_plecare",String.valueOf(aeroport_plecare.get().getId()))
                .param("aeroport_sosire",String.valueOf(aeroport_sosire.get().getId())).param("pilot",String.valueOf(pilot.get().getId())).param("avion",String.valueOf(avion.get().getId()))
                .param("destinatie",String.valueOf(destinatie.get().getId()))
                .param("plecare","2021-12-22").param("sosire","2022-01-01").param("pret",String.valueOf(450)).param("user",user_admin.get().getEmail())
                .param("parola",user_admin.get().getParola())).andExpect(status().isNotFound());

        when(clientRepo.findByEmail(user_admin.get().getEmail())).thenReturn(user_admin);
        when(aeroportRepo.findById(aeroport_plecare.get().getId())).thenReturn(aeroport_plecare);
        when(aeroportRepo.findById(aeroport_sosire.get().getId())).thenReturn(aeroport_sosire);
        when(avionRepo.findById(avion.get().getId())).thenReturn(avion);
        when(pilotRepo.findById(pilot.get().getId())).thenReturn(pilot);
        when(destinatieRepo.findById(destinatie.get().getId())).thenReturn(destinatie);
        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED).param("aeroport_plecare",String.valueOf(aeroport_plecare.get().getId()))
                .param("aeroport_sosire",String.valueOf(aeroport_sosire.get().getId())).param("pilot",String.valueOf(pilot.get().getId())).param("avion",String.valueOf(avion.get().getId()))
                .param("destinatie",String.valueOf(destinatie.get().getId()))
                .param("plecare","2021-12-22").param("sosire","2022-01-01").param("pret",String.valueOf(450)).param("user",user_admin.get().getEmail())
                .param("parola",user_admin.get().getParola())).andExpect(status().isOk());
    }



}
