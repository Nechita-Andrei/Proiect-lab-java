package proiect.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Client;
import proiect.domain.ClientRaport;
import proiect.repository.ClientRepo;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    public Client addClient(Client pilot){
        return clientRepo.save(pilot);
    }
    public void deleteClient(Integer client_id) throws Exception {
        Optional<Client> client=clientRepo.findById(client_id);
        if(!client.isPresent()) {
            throw new Exception("no client found");
        }
        clientRepo.delete(client.get());
    }

    public Iterable<Client> getClienti() {
        return clientRepo.findTop100ByOrderByIdClientDesc();
    }

    public Iterable<ClientRaport> raport3(Integer client_id){
        return clientRepo.raport3(client_id);
    }
}
