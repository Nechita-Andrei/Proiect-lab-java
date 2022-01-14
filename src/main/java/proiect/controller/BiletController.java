package proiect.controller;


import org.springframework.http.MediaType;
import proiect.domain.Client;
import proiect.domain.Zbor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.service.ClientException;
import proiect.service.BiletService;
import proiect.service.ZborException;

import java.sql.Date;
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
    public ResponseEntity<Set<Client>> getPasageri(@PathVariable("zbor_id") int zborId){
        try {
            Set<Client> pasageri= biletService.getPasageri(zborId);
            if(pasageri.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pasageri);
        }catch (ZborException zborException){
            return ResponseEntity.noContent().build();
        }
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

    @GetMapping("/client/{client_email}/verifica_discount")
    public ResponseEntity<Void> verificaDiscountClient(@PathVariable("client_email") String email){

            biletService.verificaDiscount(email);
            return ResponseEntity.ok().build();

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
