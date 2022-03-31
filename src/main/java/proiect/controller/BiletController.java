package proiect.controller;


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
import java.util.Set;

@RestController
@RequestMapping("/bilet")
public class BiletController {

    @Autowired
    private BiletService biletService;


    @GetMapping(path = "/zboruri/{client_email}")
    public ResponseEntity<Set<Zbor>> getZboruriClient(@PathVariable("client_email") String email){
        try {
            Set<Zbor> zboruri = biletService.getZboruriClient(email);
            if(zboruri.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(zboruri);
        }
        catch (ClientException clientException){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/pasageri/{zbor_id}")
    public ModelAndView getPasageri(@PathVariable("zbor_id") int zborId){
            Set<Client> pasageri= biletService.getPasageri(zborId);
            ModelAndView modelAndView=new ModelAndView("pasageri");
            modelAndView.addObject("pasageri",pasageri);
            return modelAndView;
    }

    @PostMapping(path = "/client/{client_email}/zbor/{zbor_id}")
    public ResponseEntity<Void> cumparaBilet(@PathVariable("client_email") String email, @PathVariable("zbor_id") Integer zborId){
        try {
            biletService.cumparaBilet(zborId,email);
            return ResponseEntity.ok().build();
        }catch (ZborException clientException){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/filtrare/{plecare}/{localitate}")
    public ResponseEntity<List<Zbor>> filtreazaZboruri(@PathVariable("plecare") String plecare,@PathVariable ("localitate") String localitate){
        try{
           List<Zbor>zboruri= biletService.getZboruriDataDestinatie(Date.valueOf(plecare),localitate);
           return ResponseEntity.ok(zboruri);
        }catch (ZborException zborException){
            return ResponseEntity.notFound().build();
        }
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
        redirectAttributes.addFlashAttribute("mesaj","User-ul este eligibil pentru discount si a fost salvat corespunzator!");
        redirectAttributes.addFlashAttribute("color","green");
        return new ModelAndView("redirect:/client");
    }

    @PostMapping(path = "/zbor/{zbor_id}/client/{client_email}")
    public ResponseEntity<Void> addZborClient(@PathVariable("zbor_id") int zborId, @PathVariable("client_email") String email){
        try{
            biletService.addZborClient(zborId,email);
            return ResponseEntity.ok().build();
        }catch (ZborException | ClientException clientException){
            return ResponseEntity.notFound().build();
        }
    }



}
