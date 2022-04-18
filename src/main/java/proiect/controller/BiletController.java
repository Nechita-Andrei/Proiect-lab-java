package proiect.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proiect.domain.Client;
import proiect.domain.Zbor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.service.BiletService;
import proiect.service.ClientException;
import proiect.service.ZborException;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/bilet")
@Slf4j
public class BiletController {

    @Autowired
    private BiletService biletService;


    @GetMapping(path = "/zboruri/{client_email}")
    public ModelAndView getZboruriClient(@PathVariable("client_email") String email){
            Set<Zbor> zboruri = biletService.getZboruriClient(email);
            ModelAndView modelAndView=new ModelAndView("zboruriClient");
            modelAndView.addObject("zboruri",zboruri);
            log.info("s-au afisat toate zborurile pentru clientul: "+email);
            return modelAndView;

    }

    @GetMapping(path = "/pasageri/{zbor_id}")
    public ModelAndView getPasageri(@PathVariable("zbor_id") int zborId){
            Set<Client> pasageri= biletService.getPasageri(zborId);
            ModelAndView modelAndView=new ModelAndView("pasageri");
            modelAndView.addObject("pasageri",pasageri);
            log.info("s-au afisat pasagerii pentru zborul: "+zborId);
            return modelAndView;
    }

    @RequestMapping(path = "/client/{client_email}/zbor/{zbor_id}")
    public ModelAndView cumparaBilet(@PathVariable("client_email") String email, @PathVariable("zbor_id") Integer zborId){
        biletService.cumparaBilet(zborId,email);
        log.info("s-a cumparat cu succes un bilet la zborul: "+zborId+" de catre "+email);
        return new ModelAndView("redirect:/bilet/arataBilete/client/"+email);




    }

    @RequestMapping(path = "/arataBilete/client/{client_email}")
    public ModelAndView arataBiletePosibileClient(@PathVariable("client_email") String email){
        List<Zbor>zboruri=biletService.getZboruriPosibile(email);
        ModelAndView modelAndView=new ModelAndView("zboruriPosibile");
        modelAndView.addObject("zboruri",zboruri);
        modelAndView.addObject("client",email);
        log.info("s-au afisat zborurile a caror bilete pot fi achizitionate de catre: "+email);
        return modelAndView;
    }

    @RequestMapping(path = "/filtrare")
    public ModelAndView filtreazaZboruri(@RequestParam("page") Optional<Integer> page,@RequestParam("filtrare_plecare") String plecare, @RequestParam ("filtrare_localitate") String localitate){

        if(plecare=="" || localitate==""){
            return new ModelAndView("redirect:/zbor");
        }
        ModelAndView modelAndView=new ModelAndView("zbor");
        int currentPage=page.orElse(1);
        int pageSize = 5;

        Page<Zbor> zborPage=biletService.findPaginatedFiltered(PageRequest.of(currentPage-1,pageSize),Date.valueOf(plecare),localitate);
        modelAndView.addObject("zboruri",zborPage);
        return modelAndView;
    }

    @RequestMapping ("/client/{client_email}/verifica_discount")
    public ModelAndView verificaDiscountClient(@PathVariable("client_email") String email, RedirectAttributes redirectAttributes){
            try {
                biletService.verificaDiscount(email);
            }catch (ClientException clientException){
                //ai addFlashAttribute
                redirectAttributes.addFlashAttribute("mesaj","User-ul nu este eligibil pentru discount");
                redirectAttributes.addFlashAttribute("color","red");
                return new ModelAndView("redirect:/client");
            }
            log.info("s-a verificat discount-ul pentru "+email);
        redirectAttributes.addFlashAttribute("mesaj","User-ul este eligibil pentru discount si a fost salvat corespunzator!");
        redirectAttributes.addFlashAttribute("color","green");
        return new ModelAndView("redirect:/client");
    }




}
