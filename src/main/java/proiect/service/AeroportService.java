package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Aeroport;
import proiect.repository.AeroportRepo;
import java.util.Optional;

@Service
public class AeroportService {

    @Autowired
    private AeroportRepo aeroportRepo;

    public Aeroport addAeroport(Aeroport aeroport){
        return aeroportRepo.save(aeroport);
    }

    public void deleteAeroport(Integer aeroport_id) throws Exception {
        Optional<Aeroport> aeroport= aeroportRepo.findById(aeroport_id);
        if(!aeroport.isPresent()) {
            throw new Exception("no aeroport found");
        }
        aeroportRepo.delete(aeroport.get());
    }

    public Iterable<Aeroport> getAeroporturi() {
        return aeroportRepo.findTop100ByOrderByIdAeroportDesc();
    }
}
