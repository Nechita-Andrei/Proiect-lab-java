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
public class BiletControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void test_lista_pasageri_ok() throws Exception {
        mockMvc.perform(get("/bilet/pasageri/{id_zbor}", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("pasageri"));

    }

    @Test
    public void test_lista_pasageri_not_ok() throws Exception{
        mockMvc.perform(get("/bilet/pasageri/{id_zbor}", "33"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("zborException"));
    }

}

