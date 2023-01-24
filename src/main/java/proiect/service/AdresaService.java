package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.repository.AdresaRepo;
import proiect.domain.Adresa;

import java.util.Optional;


@Service
public class AdresaService {
    @Autowired
    private AdresaRepo adresaRepo;

    public Adresa addAdresa(Adresa adresa){
        return adresaRepo.save(adresa);
    }

    public void deleteAdresa(Integer adresa_id) throws Exception {
        Optional<Adresa> adresaOptional= adresaRepo.findById(adresa_id);
        if(!adresaOptional.isPresent()) {
            throw new Exception("no adresa found");
        }
        adresaRepo.delete(adresaOptional.get());
    }

    public Iterable<Adresa> getAdrese() {
        return adresaRepo.findTop100ByOrderByIdAdresaDesc();
    }
}
