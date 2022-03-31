package proiect.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import proiect.domain.Client;
import proiect.domain.Cont;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import proiect.service.ClientException;
import proiect.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    public ModelAndView getClients(HttpServletRequest request){
        Collection<Client> clients=(Collection<Client>) clientService.findAllClients();
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        String some="";
        String color="";
        if (inputFlashMap != null) {
            some = (String) inputFlashMap.get("mesaj");
            color = (String) inputFlashMap.get("color");
        }
        ModelAndView modelAndView=new ModelAndView("clienti");
        modelAndView.addObject("clienti",clients);
        modelAndView.addObject("some",some);
        modelAndView.addObject("color",color);

        return modelAndView;
    }

    @GetMapping("/{client_id}")
    public ModelAndView getClients(@PathVariable("client_id") int id_client){
        Client client=clientService.getClientById(id_client);
        ModelAndView modelAndView=new ModelAndView("cont_client");
        modelAndView.addObject("client",client);
        return modelAndView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> addClient(String nume, String prenume, String varsta, String email, String telefon, String parola, String numar_card, String rol){
            Client client = clientService.addClient(nume, prenume, Integer.parseInt(varsta), email,
                   telefon,parola,numar_card , rol);
//            URI uri = WebMvcLinkBuilder.linkTo(ClientController.class).slash("clienti").slash(client.getId()).toUri();
//            return ResponseEntity.created(uri).build();
            return ResponseEntity.ok().build();
    }

}
