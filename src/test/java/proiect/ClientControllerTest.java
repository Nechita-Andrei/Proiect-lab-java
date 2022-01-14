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
import proiect.controller.ClientController;
import proiect.domain.Client;
import proiect.domain.Cont;
import proiect.repository.*;
import proiect.service.ClientService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ClientController.class)
@EnableTransactionManagement
@ComponentScan
public class ClientControllerTest {

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

    private static List<Client> clients;
    private static Cont cont;
    private static Client client;

    @BeforeAll
    public static void setup(){
        cont=new Cont("BCR",100000,"36126398");
        clients=new ArrayList<>();
        clients.add(new Client("test1",
                "testalaus",28,"test_gresit@yahoo.com","021638726","testt",cont, Client.Rol.CLIENT));

        client=new Client("test1",
                "testalaus",28,"test_gresit@yahoo.com","021638726","testt",cont, Client.Rol.CLIENT);
    }


    @Test
    public void testGetClients() throws Exception {
        when(clientRepo.findAll()).thenReturn(Collections.emptyList());
        String endpoint="/client";
        mockMvc.perform(get(endpoint)).andExpect(status().isNoContent());
        when(clientRepo.findAll()).thenReturn(clients);
        mockMvc.perform(get(endpoint)).andExpect(status().isOk());
    }

    @Test
    public void addCont() throws Exception{
        when(contRepo.save(cont)).thenReturn(cont);
        String endpoint="/client/cont";
        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("banca",cont.getBanca()).param("suma",String.valueOf(cont.getSumaDeBani()))
                .param("numar_Card",cont.getNumarCard())).andExpect(status().isAccepted());
    }

    @Test
    public void addClient() throws Exception{
        when(clientRepo.save(client)).thenReturn(client);
        when(contRepo.findByNumarCard(cont.getNumarCard())).thenReturn(Optional.of(cont));
        String endpoint="/client";
        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("nume",client.getNume()).param("prenume",client.getPrenume())
                .param("varsta", String.valueOf(client.getVarsta())).param("email",client.getEmail()).param("telefon",client.getTelefon())
                .param("parola",client.getParola()).param("numar_card",cont.getNumarCard()).param("rol",client.getRol().toString())).andExpect(status().isOk());

        when(contRepo.findByNumarCard(cont.getNumarCard())).thenReturn(Optional.empty());
        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("nume",client.getNume()).param("prenume",client.getPrenume())
                .param("varsta", String.valueOf(client.getVarsta())).param("email",client.getEmail()).param("telefon",client.getTelefon())
                .param("parola",client.getParola()).param("numar_card",cont.getNumarCard()).param("rol",client.getRol().toString())).andExpect(status().isBadRequest());

    }
}
