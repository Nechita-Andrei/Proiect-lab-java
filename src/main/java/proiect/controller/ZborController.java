package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.*;
import proiect.service.ZborService;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zbor")
@Slf4j
public class ZborController {

    @Autowired
    private ZborService zborService;

    @GetMapping
    public ModelAndView getZboruri(@RequestParam("page") Optional<Integer> page,@RequestParam(value = "sort", required = false) Optional<String> sort_column){
        ModelAndView modelAndView=new ModelAndView("zbor");
        int currentPage=page.orElse(1);
        int pageSize = 5;
        log.info("inainte este: "+sort_column);
        String column_to_sort=sort_column.orElse("idasc");
        log.info("se sorteaza dupa: "+column_to_sort);
        Page<Zbor> zborPage=zborService.findPaginated(PageRequest.of(currentPage-1,pageSize),column_to_sort);
        modelAndView.addObject("zboruri",zborPage);
        return modelAndView;
    }

    @RequestMapping("/avion/new")
    public ModelAndView newAvion() {
        ModelAndView modelAndView = new ModelAndView("avionForm");
        modelAndView.addObject("avion",new Avion());
        return modelAndView;
    }
    @PostMapping(path = "/avion")
    public ModelAndView addAvion(@ModelAttribute Avion avion, @RequestParam("user.name") String email, @RequestParam("user.parola") String parola){
        zborService.addAvion(avion,email,parola);
        log.info("s-a adaugat un avion");
        return new ModelAndView("redirect:/zbor");
    }

    @RequestMapping("/pilot/new")
    public ModelAndView newPilot() {
        ModelAndView modelAndView = new ModelAndView("pilotForm");
        modelAndView.addObject("pilot",new Pilot());
        return modelAndView;
    }
    @PostMapping(path = "/pilot")
    public ModelAndView addPilot(Pilot pilot, @RequestParam("user.name")String user,@RequestParam("user.parola")String parola){
        zborService.addPilot(pilot,user,parola);
        log.info("s-a adaugat un pilot");
        return new ModelAndView("redirect:/zbor");
    }



    @RequestMapping("/destinatie/new")
    public ModelAndView newDestinatie() {
        ModelAndView modelAndView = new ModelAndView("destinatieForm");
        modelAndView.addObject("destinatie",new Destinatie());
        return modelAndView;
    }
    @PostMapping(path = "/destinatie")
    public ModelAndView addDestinatie(Destinatie destinatie, @RequestParam("user.name")String user,@RequestParam("user.parola")String parola){
        zborService.addDestinatie(destinatie,user,parola);
        log.info("s-a adaugat o destinatie");
        return new ModelAndView("redirect:/zbor");
    }

    @RequestMapping("/aeroport/new")
    public ModelAndView newAeroport() {
        ModelAndView modelAndView = new ModelAndView("aeroportForm");
        modelAndView.addObject("aeroport",new Aeroport());
        return modelAndView;
    }
    @PostMapping(path = "/aeroport")
    public ModelAndView addAeroport(Aeroport aeroport,@RequestParam("user.name")String user,@RequestParam("user.parola")String parola) {
       zborService.addAeroport(aeroport,user,parola);
       log.info("s-a adaugat un aeroport");
        return new ModelAndView("redirect:/zbor");
    }

    @RequestMapping("/new")
    public ModelAndView newZbor(){
        ModelAndView modelAndView=new ModelAndView("zborForm");
        Iterable<Aeroport>aeroporturi= zborService.getAllAeroporturi();
        Iterable<Pilot> piloti= zborService.getAllPiloti();
        Iterable<Destinatie>destinatii= zborService.getAllDestinatii();
        Iterable<Avion>avioane= zborService.getAllAvioane();

        modelAndView.addObject("avioane",avioane);
        modelAndView.addObject("destinatii",destinatii);
        modelAndView.addObject("piloti",piloti);
        modelAndView.addObject("aeroporturi",aeroporturi);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addZbor(@RequestParam("aeroport_plecare") int aeroport_plecare, @RequestParam("aeroport_sosire") int aeroport_sosire, @RequestParam("pilot") int pilot,
                                        @RequestParam("avion") int avion, @RequestParam("destinatie") int destinatie, @RequestParam("plecare") String plecare, @RequestParam("sosire") String sosire,
                                        @RequestParam("pret") int pret, @RequestParam("user.name") String user, @RequestParam("user.parola") String parola){

            zborService.addZbor(aeroport_plecare,aeroport_sosire,pilot,avion,destinatie,Date.valueOf(plecare),Date.valueOf(sosire),pret,user,parola);
            log.info("s-a adaugat un zbor");
            return new ModelAndView("redirect:/zbor");

    }

    @RequestMapping("/delay/new")
    public ModelAndView newDelay() {
        ModelAndView modelAndView = new ModelAndView("delayForm");
        List<Zbor>zboruri= (List<Zbor>) zborService.getAllZboruri();
        modelAndView.addObject("zboruri",zboruri);
        return modelAndView;
    }
    @PostMapping("/delay")
    public ModelAndView adaugaDelay(@RequestParam("zbor_id") int zbor, @RequestParam("minute") int minute, @RequestParam("user.name") String email, @RequestParam("user.parola") String parola){
        zborService.adaugaDelay(zbor,minute,email,parola);
        log.info("s-a adaugat delay");
        return new ModelAndView("redirect:/zbor");
    }

}
