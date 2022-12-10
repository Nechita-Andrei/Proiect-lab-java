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
    private ClientRepo clientRepo;

    public Iterable<Zbor> getAllZboruri() {
        return zborRepo.findAll();
    }

    public Iterable<Aeroport> getAllAeroporturi() {
        return aeroportRepo.findAll();
    }

    public Iterable<Avion> getAllAvioane() {
        return avionRepo.findAll();
    }

    public Iterable<Pilot> getAllPiloti() {
        return pilotRepo.findAll();
    }


    //functie care verifica credentialele date de user


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


}
