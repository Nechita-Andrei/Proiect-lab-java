package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Avion;
import proiect.repository.AvionRepo;

import java.util.Optional;

@Service
public class AvionService {

    @Autowired
    private AvionRepo avionRepo;

    public Avion addAvion(Avion avion){
        return avionRepo.save(avion);
    }
    public void deleteAvion(Integer avion_id) throws Exception {
        Optional<Avion> avion= avionRepo.findById(avion_id);
        if(!avion.isPresent()) {
            throw new Exception("no avion found");
        }
        avionRepo.delete(avion.get());
    }

    public Iterable<Avion> getAvioane() {
        return avionRepo.findAll();
    }
}
