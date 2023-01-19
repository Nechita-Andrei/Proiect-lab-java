package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.ZonaCovid;
import proiect.repository.ZonaCovidRepo;

import java.util.Optional;

@Service
public class ZonaCovidService {

    @Autowired
    private ZonaCovidRepo zonaCovidRepo;


    public ZonaCovid addZonaCovid(ZonaCovid zonaCovid){
        return zonaCovidRepo.save(zonaCovid);
    }
    public void deleteZonaCovid(Integer zona_covid_id) throws Exception {
        Optional<ZonaCovid> zonaCovid=zonaCovidRepo.findById(zona_covid_id);
        if(!zonaCovid.isPresent()) {
            throw new Exception("no tip bilet found");
        }
        zonaCovidRepo.delete(zonaCovid.get());
    }

    public Iterable<ZonaCovid> getZoneCovid() {
        return zonaCovidRepo.findAll();
    }

}
