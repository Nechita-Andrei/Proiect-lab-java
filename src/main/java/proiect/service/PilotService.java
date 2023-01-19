package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Contact;
import proiect.domain.Pilot;
import proiect.domain.PilotRaport;
import proiect.repository.PilotRepo;

import java.util.Optional;

@Service
public class PilotService {


    @Autowired
    private PilotRepo pilotRepo;

    public Pilot addPilot(Pilot pilot){
        return pilotRepo.save(pilot);
    }
    public void deletePilot(Integer pilot_id) throws Exception {
        Optional<Pilot> pilot=pilotRepo.findById(pilot_id);
        if(!pilot.isPresent()) {
            throw new Exception("no pilot found");
        }
        pilotRepo.delete(pilot.get());
    }
    public Iterable<PilotRaport> raport1(){
        return pilotRepo.raport1();
    }

    public Iterable<Pilot> getPiloti() {
        return pilotRepo.findAll();
    }
}
