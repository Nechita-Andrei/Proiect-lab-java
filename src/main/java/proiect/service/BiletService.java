package proiect.service;

import proiect.domain.Client;
import proiect.domain.Destinatie;
import proiect.domain.Zbor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.repository.ClientRepo;
import proiect.repository.DestinatieRepo;
import proiect.repository.ZborRepo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BiletService {

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ZborRepo zborRepo;

    @Autowired
    private DestinatieRepo destinatieRepo;



//intoarce lista pasagerilor unui anumit zbor
    public Set<Client> getPasageri(int idZbor){
        Optional <Zbor> zbor=zborRepo.findById(idZbor);
        if(!zbor.isPresent()){
            throw ZborException.zborNotFound();
        }
        return zbor.get().getPasageri();
    }

    //intoarce lista zborurilor filtrate dupa ziua plecarii si a localitatii
    public List<Zbor> getZboruriDataDestinatie(Date date, String localitate){
        Optional<Destinatie> destinatie=destinatieRepo.findByLocalitate(localitate);
        if(!destinatie.isPresent()){
            throw ZborException.destinatieNotFound();
        }

        List<Zbor> rezultat = new ArrayList<>();
        Iterable<Zbor> zboruri=zborRepo.findAll();
        for(Zbor zbor : zboruri){
            if (zbor.getData_plecare().equals(date) && zbor.getDestinatie().getLocalitate().equals(localitate)){
                rezultat.add(zbor);
            }
        }
        if(rezultat.isEmpty()){
            throw ZborException.zborNotFound();
        }
        return rezultat;

    }

//returneaza toate zborurile unui client anume
    public Set<Zbor> getZboruriClient(String email){
        Optional <Client> client=clientRepo.findByEmail(email);
        if(!client.isPresent()){
            throw ClientException.clientNotFound();
        }
        return client.get().getZboruri();
    }

    //verifica daca un client este eligibil pentru discount(daca a cumparat mai mult de 10 bilete)
    public void verificaDiscount(String email){
        Optional <Client> client=clientRepo.findByEmail(email);
        if(!client.isPresent()){
            throw ClientException.clientNotFound();
        }
        if(client.get().getZboruri().size()>=10){
            client.get().setDiscount(true);
            clientRepo.save(client.get());
        }
        else {
            client.get().setDiscount(false);
            clientRepo.save(client.get());
            throw ClientException.notEligibleForDiscount();
        }
    }

    //adauga un zbor unui anumit client
    public void addZborClient(int idZbor, String email){
        Optional <Client> client=clientRepo.findByEmail(email);
        if(!client.isPresent()){
            throw ClientException.clientNotFound();
        }else {
            Optional<Zbor> zbor=zborRepo.findById(idZbor);
            if(!zbor.isPresent()){
                throw ZborException.zborNotFound();
            }
            else {
                client.get().addZbor(zbor.get());
                clientRepo.save(client.get());
            }
        }
    }
    //procesul de cumparare bilet...a unui zbor de catre un anumit client, acestuia din urma i se scade suma din cont corespunzatoare biletului
    public void cumparaBilet(int idZbor, String email){
        Optional <Zbor> zbor=zborRepo.findById(idZbor);
        if(!zbor.isPresent()){
            throw ZborException.zborNotFound();
        }
        else {
            Optional<Client> client=clientRepo.findByEmail(email);
            if(!client.isPresent()){
                throw ClientException.clientNotFound();
            }
            if (client.get().getRol()!= Client.Rol.CLIENT){
                throw ClientException.noPermission();
            }
                if(client.get().getCont().getSumaDeBani()<zbor.get().getPret()){
                    throw ClientException.notEnoughMoney();
                }
                if(client.get().isDiscount()){
                    client.get().getCont().setSumaDeBani(client.get().getCont().getSumaDeBani() - zbor.get().getPret() + zbor.get().getPret()/10);
                }
                else {
                    client.get().getCont().setSumaDeBani(client.get().getCont().getSumaDeBani() - zbor.get().getPret());
                }
                zbor.get().addPasager(client.get());
                zborRepo.save(zbor.get());

        }
    }
}
