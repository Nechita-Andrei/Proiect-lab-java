package proiect.controller;

import org.dom4j.rule.Mode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.*;
import proiect.service.ClientException;
import proiect.service.ZborException;
import proiect.service.ZborService;

import java.net.URI;
import java.sql.Date;
import java.util.Collection;

@RestController
@RequestMapping("/zbor")
public class ZborController {

    @Autowired
    private ZborService zborService;

    @GetMapping
    public ModelAndView getZboruri(){
        ModelAndView modelAndView=new ModelAndView("zbor");
        Collection<Zbor> zboruri=(Collection<Zbor>) zborService.getAllZboruri();
        modelAndView.addObject("zboruri",zboruri);
        return modelAndView;
    }

    @PostMapping(path = "/avion",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void>addAvion(String model, int numar_locuri, int an_constructie, String user, String parola){
            Avion avion=zborService.addAvion(model,numar_locuri,an_constructie,user,parola);
//            URI uri= WebMvcLinkBuilder.linkTo(ZborController.class).slash("avion").slash(avion.getId()).toUri();
//            return ResponseEntity.created(uri).build();
            return ResponseEntity.ok().build();

    }

    @PostMapping(path = "/pilot",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> addPilot(String nume, String prenume,int varsta, double salariu, String experienta,String user,String parola){
        try{
            Pilot pilot=zborService.addPilot(nume,prenume,varsta,salariu,experienta,user,parola);
            URI uri= WebMvcLinkBuilder.linkTo(ZborController.class).slash("pilot").slash(pilot.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }catch (ClientException| DataIntegrityViolationException clientException){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/destinatie",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> addDestinatie(String tara, String localitate, String zona_covid,String user, String parola){
        try {
            Destinatie destinatie=zborService.addDestinatie(tara,localitate,zona_covid,user,parola);
            URI uri= WebMvcLinkBuilder.linkTo(ZborController.class).slash("destinatie").slash(destinatie.getId()).toUri();
            return ResponseEntity.created(uri).build();

        }catch (ClientException| DataIntegrityViolationException clientException){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/aeroport",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> addAeroport(String denumire, String localitate, int capacitate,String user,String parola) {
        try {
            Aeroport aeroport = zborService.addAeroport(denumire, localitate, capacitate, user, parola);
            URI uri = WebMvcLinkBuilder.linkTo(ZborController.class).slash("aeroport").slash(aeroport.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (ClientException| DataIntegrityViolationException clientException) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> addZbor(int aeroport_plecare, int aeroport_sosire, int pilot, int avion, int destinatie, String plecare, String sosire, int pret, String user, String parola){
        try {
            Zbor zbor = zborService.addZbor(aeroport_plecare, aeroport_sosire, pilot, avion, destinatie, Date.valueOf(plecare), Date.valueOf(sosire),pret,user,parola);
//            URI uri= WebMvcLinkBuilder.linkTo(ZborController.class).slash("zboruri").slash(zbor.getId()).toUri();
//            return ResponseEntity.created(uri).build();
            return ResponseEntity.ok().build();
        }catch (ZborException| DataIntegrityViolationException zborException){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/delay/{zbor_id}/{minute}/{email}/{parola}")

    public ResponseEntity<Void>adaugaDelay(@PathVariable("zbor_id") int zbor, @PathVariable("minute") int minute, @PathVariable("email") String email, @PathVariable("parola") String parola){
        try{
            zborService.adaugaDelay(zbor,minute,email,parola);
            return ResponseEntity.ok().build();
        }catch (ClientException | ZborException exception){
            return ResponseEntity.noContent().build();
        }
    }

}
