package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Oras;
import proiect.repository.OrasRepo;


import java.util.Optional;

@Service
public class OrasService {

    @Autowired
    private OrasRepo orasRepo;


    public Oras addOras(Oras oras){
        return orasRepo.save(oras);
    }
    public void deleteOras(Integer oras_id) throws Exception {
        Optional<Oras> oras=orasRepo.findById(oras_id);
        if(!oras.isPresent()) {
            throw new Exception("no tip bilet found");
        }
        orasRepo.delete(oras.get());
    }

    public Iterable<Oras> getOrase() {
        return orasRepo.findAll();
    }

}
