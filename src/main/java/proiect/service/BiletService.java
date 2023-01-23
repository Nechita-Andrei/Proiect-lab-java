package proiect.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import proiect.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.repository.BiletRepo;
import proiect.repository.ClientRepo;
import proiect.repository.ZborRepo;

import java.sql.Date;
import java.util.*;

@Service
@Slf4j
public class BiletService {

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ZborRepo zborRepo;
    @Autowired
    private BiletRepo biletRepo;

    public Bilet addBilet(Bilet bilet){
        return biletRepo.save(bilet);
    }

    public void deleteBilet(Integer id_client, Integer id_zbor) throws Exception {
        Optional<Bilet> bilet= biletRepo.findById(new BiletId(id_client, id_zbor));
        if(!bilet.isPresent()) {
            throw new Exception("no bilet found");
        }
        biletRepo.delete(bilet.get());
    }

    public Iterable<Bilet> getBilete() {
        return biletRepo.findAll();
    }
    public SumaBileteVanduteAn raport() {
        return biletRepo.raport();
    }


//intoarce lista pasagerilor unui anumit zbor



    public Page<Zbor> findPaginatedFiltered(Pageable pageable, Date date, String localitate) {
        log.info("se apeleaza paginarea cu filtrare pentru pagina: "+pageable.getPageNumber());
        List<Zbor> zboruri = (List<Zbor>) getZboruriDataDestinatie(date,localitate);
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

    //intoarce lista zborurilor filtrate dupa ziua plecarii si a localitatii
    public List<Zbor> getZboruriDataDestinatie(Date date, String localitate){
//        Optional<Destinatie> destinatie=destinatieRepo.findByLocalitate(localitate);
//        if(!destinatie.isPresent()){
//            throw ZborException.destinatieNotFound();
//        }

        List<Zbor> rezultat = new ArrayList<>();
        Iterable<Zbor> zboruri=zborRepo.findAll();
//        for(Zbor zbor : zboruri){
//            if (zbor.getData_plecare().equals(date) && zbor.getDestinatie().getLocalitate().equals(localitate)){
//                rezultat.add(zbor);
//            }
//        }
        if(rezultat.isEmpty()){
            throw ZborException.zborNotFound();
        }
        return rezultat;

    }



}
