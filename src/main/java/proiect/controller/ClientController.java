package proiect.controller;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import proiect.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import proiect.domain.Cont;
import proiect.service.ClientException;
import proiect.service.ClientService;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    public ResponseEntity<Iterable<Client>> getClients(){
        Collection<Client> clients=(Collection<Client>) clientService.findAllClients();
        if(clients.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }

    @PostMapping(path = "/cont",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> addCont(String banca, int suma, String numar_card) {
        try {
            Cont cont = clientService.addCont(banca, suma, numar_card);
            return ResponseEntity.accepted().build();

        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> addClient(String nume, String prenume, String varsta, String email, String telefon, String parola, String numar_card, String rol){
        try {
            Client client = clientService.addClient(nume, prenume, Integer.parseInt(varsta), email,
                   telefon,parola,numar_card , rol);
//            URI uri = WebMvcLinkBuilder.linkTo(ClientController.class).slash("clienti").slash(client.getId()).toUri();
//            return ResponseEntity.created(uri).build();
            return ResponseEntity.ok().build();
        }catch (ClientException |DataIntegrityViolationException dataIntegrityViolationException){
            return ResponseEntity.badRequest().build();
        }
    }

}
