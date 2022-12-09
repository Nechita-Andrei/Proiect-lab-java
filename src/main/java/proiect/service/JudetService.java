package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Judet;
import proiect.domain.Tara;
import proiect.repository.JudetRepo;

import java.util.Optional;

@Service
public class JudetService {
    @Autowired
    private JudetRepo judetRepo;


    public Judet addJudet(Judet judet){
        return judetRepo.save(judet);
    }
    public void deleteJudet(Integer judet_id) throws Exception {
        Optional<Judet> judet=judetRepo.findById(judet_id);
        if(!judet.isPresent()) {
            throw new Exception("no tip bilet found");
        }
        judetRepo.delete(judet.get());
    }

    public Iterable<Judet> getJudete() {
        return judetRepo.findAll();
    }
}
