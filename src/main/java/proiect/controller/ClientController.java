package proiect.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import proiect.domain.Adresa;
import proiect.domain.Client;
import proiect.domain.Cont;
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
        log.info("se afiseaza toti clientii...");
        return modelAndView;
    }

    @GetMapping("/{client_id}")
    public ModelAndView getContClient(@PathVariable("client_id") int id_client){
        Client client=clientService.getClientById(id_client);
        ModelAndView modelAndView=new ModelAndView("cont_client");
        modelAndView.addObject("client",client);
        log.info("se afiseaza detaliile contului pentru clientul "+client.getNume()+' '+client.getPrenume());
        return modelAndView;
    }


    @PostMapping("/cont")
    public ModelAndView addCont(@ModelAttribute Cont cont, @RequestParam("adresa") Integer adresa_id){
        clientService.addCont(cont);
        log.info("s-a adaugat cu succes contul");
        return new ModelAndView("redirect:/client/new/"+adresa_id+"/"+cont.getId());
    }

    @PostMapping("/adresa")
    public ModelAndView addAdresa(@ModelAttribute Adresa adresa){
        clientService.addAdresa(adresa);
        log.info("s-a adaugat cu succes adresa");
        return new ModelAndView("redirect:/client/cont/new/"+adresa.getId());
    }
    @RequestMapping("/adresa/new")
      public ModelAndView newAdresa() {
        ModelAndView modelAndView = new ModelAndView("adresaForm");
        modelAndView.addObject("adresa",new Adresa());
        return modelAndView;
    }


    @RequestMapping("/cont/new/{adresa_id}")
    public ModelAndView newCont(@PathVariable("adresa_id") Integer adresa_id){
        ModelAndView modelAndView=new ModelAndView("contForm");
        modelAndView.addObject("cont",new Cont());
        modelAndView.addObject("adresa",adresa_id);
        return modelAndView;
    }

    @RequestMapping("/new/{id_adresa}/{id_cont}")
    public ModelAndView newClient(@PathVariable("id_cont") int cont_id, @PathVariable("id_adresa") Integer adresa_id){
        ModelAndView modelAndView=new ModelAndView("clientForm");
        modelAndView.addObject("client",new Client());
        Cont cont= clientService.findContById(cont_id);
        modelAndView.addObject("cont",cont);
        Adresa adresa=clientService.findAdresaById(adresa_id);
        modelAndView.addObject("adresa",adresa);
        return modelAndView;
    }

    @RequestMapping("/delete/{id_user}")
    public ModelAndView deleteClient(@PathVariable("id_user") int id_user){
        clientService.deleteClient(id_user);
        return new ModelAndView("redirect:/client");
    }

    @PostMapping
    public ModelAndView addClient(@RequestParam("nume") String nume, @RequestParam("prenume") String prenume,@RequestParam("varsta") int varsta,
                                  @RequestParam("email") String email,@RequestParam("telefon")  String telefon,@RequestParam("parola") String parola,
                                  @RequestParam("rol") String rol,@RequestParam("cont.numarCard") String numar_card, @RequestParam("adresa_id") Integer adresa_id){
        log.info("numele: "+nume);
        log.info("prenume: "+prenume);
        log.info("varsta: "+varsta);
        log.info("email: "+email);
        log.info("telefon: "+telefon);
        log.info("parola: "+parola);
        log.info("numar_card: "+numar_card);
        log.info("rol: "+rol);
        log.info("adresa: "+adresa_id);
        clientService.addClient(nume,prenume,varsta,email,telefon,parola,numar_card,rol,adresa_id);
        return new ModelAndView("redirect:/client");
    }

}
