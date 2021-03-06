package proiect.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import proiect.domain.*;
import proiect.repository.*;

import java.sql.Date;
import java.util.ArrayList;
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

    public Iterable<Zbor> getAllZboruri() {
        return zborRepo.findAll();
    }

    public Iterable<Aeroport> getAllAeroporturi() {
        return aeroportRepo.findAll();
    }

    public Iterable<Destinatie> getAllDestinatii() {
        return destinatieRepo.findAll();
    }

    public Iterable<Avion> getAllAvioane() {
        return avionRepo.findAll();
    }

    public Iterable<Pilot> getAllPiloti() {
        return pilotRepo.findAll();
    }


    //functie care verifica credentialele date de user
    public boolean logare(Client client, String parola) {

        return client.getParola().equals(parola);

    }

    public Page<Zbor> findPaginated(Pageable pageable, String column) {
        log.info("se apeleaza paginarea pentru pagina: " + pageable.getPageNumber());
        List<Zbor> zboruri;
        if (column == "idasc") {
            zboruri = (List<Zbor>) zborRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        } else {
            zboruri = (List<Zbor>) zborRepo.findAll(Sort.by(Sort.Direction.DESC, column));
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Zbor> list;
        if (zboruri.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, zboruri.size());
            list = zboruri.subList(startItem, toIndex);
        }
        Page<Zbor> zborPage
                = new PageImpl<Zbor>(list, PageRequest.of(currentPage,
                pageSize), zboruri.size());
        return zborPage;
    }


    public Avion addAvion(Avion avion, String email, String parola) throws DataIntegrityViolationException {
        Optional<Client> client = clientRepo.findByEmail(email);
        if (!client.isPresent()) {
            throw ClientException.clientNotFound();
        }
        if (client.get().getRol() != Client.Rol.ADMIN || !logare(client.get(), parola)) {
            throw ClientException.noPermission();
        }
        return avionRepo.save(avion);

    }

    public Pilot addPilot(Pilot pilot, String email, String parola) throws DataIntegrityViolationException {
        Optional<Client> client = clientRepo.findByEmail(email);
        if (!client.isPresent()) {
            throw ClientException.clientNotFound();
        }
        if (client.get().getRol() != Client.Rol.ADMIN || !logare(client.get(), parola)) {
            throw ClientException.noPermission();
        }
        return pilotRepo.save(pilot);

    }

    public Destinatie addDestinatie(Destinatie destinatie, String email, String parola) throws DataIntegrityViolationException {
        Optional<Client> client = clientRepo.findByEmail(email);
        if (!client.isPresent()) {
            throw ClientException.clientNotFound();
        }
        if (client.get().getRol() != Client.Rol.ADMIN || !logare(client.get(), parola)) {
            throw ClientException.noPermission();
        }
        return destinatieRepo.save(destinatie);
    }

    //functie prin care admin-ul poate adauga intarzieri unui anumit zbor
    public void adaugaDelay(int zbor_id, int minute, String email, String parola) {
        Optional<Client> user = clientRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw ClientException.clientNotFound();
        }
        if (!logare(user.get(), parola) || user.get().getRol() != Client.Rol.ADMIN) {
            throw ClientException.noPermission();
        }
        Optional<Zbor> zbor = zborRepo.findById(zbor_id);
        if (!zbor.isPresent()) {
            throw ZborException.zborNotFound();
        }
        zbor.get().setDelay(minute);
        zborRepo.save(zbor.get());

    }

    public Aeroport addAeroport(Aeroport aeroport, String email, String parola) throws DataIntegrityViolationException {
        Optional<Client> client = clientRepo.findByEmail(email);
        if (!client.isPresent()) {
            throw ClientException.clientNotFound();
        }
        if (client.get().getRol() != Client.Rol.ADMIN || !logare(client.get(), parola)) {
            throw ClientException.noPermission();
        }
        return aeroportRepo.save(aeroport);
    }


    public Zbor addZbor(int aeroport_plecareID, int aeroport_sosireID, int pilot_id, int avion_id, int destinatie_id, Date plecare, Date sosire, int pret, String email, String parola) throws DataIntegrityViolationException {
        Optional<Client> client = clientRepo.findByEmail(email);
        if (!client.isPresent()) {
            throw ClientException.clientNotFound();
        }
        log.info("rol: " + client.get().getRol());
        log.info("logare: " + logare(client.get(), parola));
        if (client.get().getRol() != Client.Rol.ADMIN || !logare(client.get(), parola)) {
            throw ClientException.noPermission();
        }
        Optional<Aeroport> aeroport_plecare = aeroportRepo.findById(aeroport_plecareID);
        if (!aeroport_plecare.isPresent()) {
            throw ZborException.aeroportNotFound();
        }
        Optional<Aeroport> aeroport_sosire = aeroportRepo.findById(aeroport_sosireID);
        if (!aeroport_sosire.isPresent()) {
            throw ZborException.aeroportNotFound();
        }

        Optional<Pilot> pilot = pilotRepo.findById(pilot_id);
        if (!pilot.isPresent()) {
            throw ZborException.pilotNotFound();
        }

        Optional<Avion> avion = avionRepo.findById(avion_id);
        if (!avion.isPresent()) {
            throw ZborException.avionNotFound();
        }

        Optional<Destinatie> destinatie = destinatieRepo.findById(destinatie_id);
        if (!destinatie.isPresent()) {
            throw ZborException.destinatieNotFound();
        }
        return zborRepo.save(new Zbor(aeroport_plecare.get(), aeroport_sosire.get(), avion.get(), pilot.get(), destinatie.get(), plecare, sosire, pret));


    }
}
