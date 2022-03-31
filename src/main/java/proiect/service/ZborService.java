package proiect.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proiect.domain.*;
import proiect.repository.*;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ZborService {

    @Autowired
    private ZborRepo zborRepo;

    @Autowired
    private AeroportRepo aeroportRepo;

    @Autowired
    private AvionRepo avionRepo;

    @Autowired
    private PilotRepo pilotRepo;

    @Autowired
    private DestinatieRepo destinatieRepo;

    @Autowired
    private ClientRepo clientRepo;

    public Iterable<Zbor> getAllZboruri(){
        return zborRepo.findAll();
    }

    //functie care verifica credentialele date de user
    public boolean logare(Client client, String parola){

        return client.getParola().equals(parola);

    }
    public Page<Zbor> findPaginated(Pageable pageable) {
        log.info("se apeleaza paginarea pentru pagina: "+pageable.getPageNumber());
        List<Zbor> movies = (List<Zbor>) zborRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Zbor> list;
        if (movies.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, movies.size());
            list = movies.subList(startItem, toIndex);
        }
        Page<Zbor> moviePage
                = new PageImpl<Zbor>(list, PageRequest.of(currentPage,
                pageSize), movies.size());
        return moviePage;
    }


    public Avion addAvion(String model, int numar_locuri, int an_constructie, String email, String parola) throws DataIntegrityViolationException {
        Optional<Client>client= clientRepo.findByEmail(email);
        if(!client.isPresent()){
            throw ClientException.clientNotFound();
        }
        if(client.get().getRol()!= Client.Rol.ADMIN && logare(client.get(),parola)){
            throw ClientException.noPermission();
        }
        return avionRepo.save(new Avion(model,numar_locuri,an_constructie));

    }

    public Pilot addPilot(String nume, String prenume, int vartsa, double salariu, String experienta, String email, String parola) throws DataIntegrityViolationException{
        Optional<Client>client= clientRepo.findByEmail(email);
        if(!client.isPresent()){
            throw ClientException.clientNotFound();
        }
        if(client.get().getRol()!= Client.Rol.ADMIN && logare(client.get(),parola)){
            throw ClientException.noPermission();
        }
        return pilotRepo.save(new Pilot(nume,prenume,vartsa,salariu, Pilot.Experienta.valueOf(experienta)));

    }

    public Destinatie addDestinatie(String tara, String localitate, String zona_covid,String email, String parola)throws DataIntegrityViolationException{
        Optional<Client>client= clientRepo.findByEmail(email);
        if(!client.isPresent()){
            throw ClientException.clientNotFound();
        }
        if(client.get().getRol()!= Client.Rol.ADMIN && !logare(client.get(),parola)){
            throw ClientException.noPermission();
        }
        return destinatieRepo.save(new Destinatie(tara,localitate, Destinatie.Zona_covid.valueOf(zona_covid)));
    }

    //functie prin care admin-ul poate adauga intarzieri unui anumit zbor
   public void adaugaDelay(int zbor_id,int minute, String email, String parola){
        Optional<Client> user=clientRepo.findByEmail(email);
        if(!user.isPresent()){
            throw ClientException.clientNotFound();
        }
        if(!logare(user.get(),parola) || user.get().getRol()!= Client.Rol.ADMIN){
            throw ClientException.noPermission();
        }
        Optional<Zbor> zbor=zborRepo.findById(zbor_id);
        if(!zbor.isPresent()){
            throw ZborException.zborNotFound();
        }
        zbor.get().setDelay(minute);
        zborRepo.save(zbor.get());

   }

    public Aeroport addAeroport(String denumire, String localitate, int capacitate, String email, String parola)throws DataIntegrityViolationException{
        Optional<Client>client= clientRepo.findByEmail(email);
        if(!client.isPresent()){
            throw ClientException.clientNotFound();
        }
        if(client.get().getRol()!= Client.Rol.ADMIN && logare(client.get(),parola)){
            throw ClientException.noPermission();
        }
        return aeroportRepo.save(new Aeroport(denumire,localitate,capacitate));
    }


    public Zbor addZbor(int aeroport_plecareID, int aeroport_sosireID, int pilot_id, int avion_id, int destinatie_id, Date plecare, Date sosire, int pret,String email, String parola)throws DataIntegrityViolationException{
        Optional<Client>client= clientRepo.findByEmail(email);
        if(!client.isPresent()){
            throw ClientException.clientNotFound();
        }
        if(client.get().getRol()!= Client.Rol.ADMIN && logare(client.get(),parola)){
            throw ClientException.noPermission();
        }
        Optional<Aeroport> aeroport_plecare=aeroportRepo.findById(aeroport_plecareID);
        if(!aeroport_plecare.isPresent()){
            throw ZborException.aeroportNotFound();
        }
        Optional<Aeroport> aeroport_sosire=aeroportRepo.findById(aeroport_sosireID);
        if(!aeroport_sosire.isPresent()){
            throw ZborException.aeroportNotFound();
        }

        Optional<Pilot> pilot=pilotRepo.findById(pilot_id);
        if(!pilot.isPresent()){
            throw ZborException.pilotNotFound();
        }

        Optional<Avion> avion=avionRepo.findById(avion_id);
        if(!avion.isPresent()){
            throw ZborException.avionNotFound();
        }

        Optional<Destinatie> destinatie=destinatieRepo.findById(destinatie_id);
        if(!destinatie.isPresent()){
            throw  ZborException.destinatieNotFound();
        }
       return zborRepo.save(new Zbor(aeroport_plecare.get(),aeroport_sosire.get(),avion.get(),pilot.get(),destinatie.get(),plecare,sosire,pret));


    }
}
