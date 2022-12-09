package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.CompanieAeriana;
import proiect.domain.TipBilet;
import proiect.repository.TipBiletRepo;

import java.util.Optional;

@Service
public class TipBiletService {

    @Autowired
    private TipBiletRepo tipBiletRepo;

    public TipBilet addTipBilet(TipBilet tipBilet){
        return tipBiletRepo.save(tipBilet);
    }
    public void deleteTipBilet(Integer tip_bilet_id) throws Exception {
        Optional<TipBilet> tipBilet=tipBiletRepo.findById(tip_bilet_id);
        if(!tipBilet.isPresent()) {
            throw new Exception("no tip bilet found");
        }
        tipBiletRepo.delete(tipBilet.get());
    }

    public Iterable<TipBilet> getTipuriBilet() {
        return tipBiletRepo.findAll();
    }
}
