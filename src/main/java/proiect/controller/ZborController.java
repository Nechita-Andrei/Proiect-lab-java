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


    @RequestMapping("/destinatie/new")
    public ModelAndView newDestinatie() {
        ModelAndView modelAndView = new ModelAndView("destinatieForm");
        modelAndView.addObject("destinatie",new Destinatie());
        return modelAndView;
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



    @RequestMapping("/delay/new")
    public ModelAndView newDelay() {
        ModelAndView modelAndView = new ModelAndView("delayForm");
        List<Zbor>zboruri= (List<Zbor>) zborService.getAllZboruri();
        modelAndView.addObject("zboruri",zboruri);
        return modelAndView;
    }

}
