package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Tara;
import proiect.domain.TipBilet;
import proiect.repository.TaraRepo;

import java.util.Optional;

@Service
public class TaraService {
    @Autowired
    private TaraRepo taraRepo;


    public Tara addTara(Tara tara){
        return taraRepo.save(tara);
    }
    public void deleteTara(Integer tara_id) throws Exception {
        Optional<Tara> tara=taraRepo.findById(tara_id);
        if(!tara.isPresent()) {
            throw new Exception("no tip bilet found");
        }
        taraRepo.delete(tara.get());
    }

    public Iterable<Tara> getTari() {
        return taraRepo.findAll();
    }

}
