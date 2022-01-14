package proiect;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiect.repository.ClientRepo;
import proiect.repository.ContRepo;
import proiect.service.ClientException;
import proiect.service.ClientService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepo clientRepo;

    @Mock
    private ContRepo contRepo;



    @Test
    public void testAddClient(){
        ClientException exception=assertThrows(ClientException.class,()->clientService.addClient("test1",
                "testalaus",28,"test_gresit@yahoo.com","021638726","testt","12312","CLIENT"));
        assertEquals(ClientException.ErrorCodeClient.ACCOUNT_NOT_FOUND,exception.getErrorCodeClient());
    }

    @Test
    public void testGetClientByEmail(){
        ClientException exception=assertThrows(ClientException.class,()->clientService.getClientByEmail("test_gresit@yahoo.com"));
        assertEquals(ClientException.ErrorCodeClient.CLIENT_NOT_FOUND,exception.getErrorCodeClient());
    }

}
