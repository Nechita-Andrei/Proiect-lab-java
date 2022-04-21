package proiect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void client_cont_bun() throws Exception {
        mockMvc.perform(get("/client/{id}","3"))
                .andExpect(status().isOk())
                .andExpect(view().name("cont_client"));

    }

    @Test
    public void clinet_cont_rau() throws Exception {
        mockMvc.perform(get("/client/{id}","44"))
                .andExpect(status().isUnauthorized());

    }

}
