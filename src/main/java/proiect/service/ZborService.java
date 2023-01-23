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

    public Zbor addZbor(Zbor zbor){
        return zborRepo.save(zbor);
    }
    public void deleteZbor(Integer zbor_id) throws Exception {
        Optional<Zbor> zbor=zborRepo.findById(zbor_id);
        if(!zbor.isPresent()) {
            throw new Exception("no zbor found");
        }
        zborRepo.delete(zbor.get());
    }

    public Iterable<Zbor> getZboruri() {
        return zborRepo.findAll();
    }






}
