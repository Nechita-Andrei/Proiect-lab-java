package proiect.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import proiect.domain.Client;
import proiect.domain.Zbor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


//intoarce lista pasagerilor unui anumit zbor
    public Set<Client> getPasageri(int idZbor){
        Optional <Zbor> zbor=zborRepo.findById(idZbor);
        if(!zbor.isPresent()){
            throw ZborException.zborNotFound();
        }
        return zbor.get().getPasageri();
    }


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
