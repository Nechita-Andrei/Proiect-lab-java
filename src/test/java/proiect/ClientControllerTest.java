package proiect;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import proiect.domain.Client;
import proiect.domain.Cont;
import proiect.repository.ClientRepo;
import proiect.service.ClientService;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;


    @Test
    @WithMockUser(username = "admin", password = "test1", roles = "ADMIN")
    public void client_cont_bun() throws Exception {

        mockMvc.perform(get("/client/{id}", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("cont_client"));

    }

    @Test
    @WithMockUser(username = "admin", password = "test1", roles = "ADMIN")
    public void client_cont_rau() throws Exception {
        mockMvc.perform(get("/client/{id}", "44"))
                .andExpect(status().isUnauthorized());

    }

}
