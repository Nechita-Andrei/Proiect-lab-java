package proiect.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.CompanieAeriana;
import proiect.repository.CompanieAerianaRepo;

import java.util.Optional;

@Service
public class CompanieAerianaService {


    @Autowired
    private CompanieAerianaRepo companieAerianaRepo;

    public CompanieAeriana addCompanieAeriana(CompanieAeriana companieAeriana){
        return companieAerianaRepo.save(companieAeriana);
    }
    public void deleteCompanieAeriana(Integer companie_aeriana_id) throws Exception {
        Optional<CompanieAeriana> companieAeriana=companieAerianaRepo.findById(companie_aeriana_id);
        if(!companieAeriana.isPresent()) {
            throw new Exception("no companieAeriana found");
        }
        companieAerianaRepo.delete(companieAeriana.get());
    }

    public Iterable<CompanieAeriana> getCompaniiAeriane() {
        return companieAerianaRepo.findAll();
    }
}
