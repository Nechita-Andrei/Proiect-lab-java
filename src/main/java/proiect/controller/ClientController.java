package proiect.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
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
@Slf4j
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


    @PostMapping("/cont")
    public ModelAndView addCont(@ModelAttribute Cont cont){
        clientService.addCont(cont);
        return new ModelAndView("redirect:/client");
    }

    @RequestMapping("/cont/new")
    public ModelAndView newCont(){
        ModelAndView modelAndView=new ModelAndView("contForm");
        modelAndView.addObject("cont",new Cont());
        return modelAndView;
    }

    @RequestMapping("/new/{id_cont}")
    public ModelAndView newClient(@PathVariable("id_cont") int cont_id){
        ModelAndView modelAndView=new ModelAndView("clientForm");
        modelAndView.addObject("client",new Client());
        Cont cont= clientService.findContById(cont_id);
        modelAndView.addObject("cont",cont);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addClient(@RequestParam("nume") String nume, @RequestParam("prenume") String prenume,@RequestParam("varsta") int varsta,
                                  @RequestParam("email") String email,@RequestParam("telefon")  String telefon,@RequestParam("parola") String parola,@RequestParam("rol") String rol,@RequestParam("cont.numarCard") String numar_card){
        log.info("numele: "+nume);
        log.info("prenume: "+prenume);
        log.info("varsta: "+varsta);
        log.info("email: "+email);
        log.info("telefon: "+telefon);
        log.info("parola: "+parola);
        log.info("numar_card: "+numar_card);
        log.info("rol: "+rol);
        clientService.addClient(nume,prenume,varsta,email,telefon,parola,numar_card,rol);
        return new ModelAndView("redirect:/client");
    }

}
