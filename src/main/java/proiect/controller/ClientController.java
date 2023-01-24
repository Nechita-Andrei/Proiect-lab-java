package proiect.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Client;
import proiect.service.ClientService;
import proiect.service.ContactService;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContactService contactService;


    @PostMapping
    public ModelAndView addClient(@ModelAttribute Client client) {
        Client client_res= clientService.addClient(client);
        log.info("s-a adaugat cu succes un client nou: "+client_res.toString());
        return new ModelAndView("redirect:/client");
    }

    @GetMapping
    public ModelAndView getClienti() {
        Iterable<Client> clienti = clientService.getClienti();
        ModelAndView modelAndView = new ModelAndView("clienti");
        modelAndView.addObject("clienti", clienti);
        modelAndView.addObject("vanzari", clientService.raport3(1));
        return modelAndView;

    }

    @RequestMapping("/delete/{id_client}")
    public ModelAndView deleteClient(@PathVariable("id_client") int id_client) throws Exception {
        clientService.deleteClient(id_client);
        return new ModelAndView("redirect:/client");
    }

    @RequestMapping("/vanzari/{id_client}")
    public ModelAndView getVanzariClient(@PathVariable("id_client") int id_client) throws Exception {
        ModelAndView modelAndView=new ModelAndView("clientBilete");
        modelAndView.addObject("vanzari", clientService.raport3(id_client));
        return modelAndView;
    }
    @RequestMapping("/new")
    public ModelAndView newClient() {
        ModelAndView modelAndView = new ModelAndView("clientForm");
        modelAndView.addObject("client", new Client());
        modelAndView.addObject("contacte", contactService.getContacte());
        return modelAndView;
    }

}
