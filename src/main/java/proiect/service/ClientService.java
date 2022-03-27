package proiect.service;


import proiect.domain.Client;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Cont;
import proiect.repository.ClientRepo;
import proiect.repository.ContRepo;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    public ContRepo contRepo;

    //adaugare cont
    public Cont addCont(String banca, int suma, String numar_card) throws DataIntegrityViolationException{
        return contRepo.save(new Cont(banca,suma, numar_card));
    }

    //returneaza un client dupa un email dat
    public Client getClientByEmail(String email){
        Optional<Client> client=clientRepo.findByEmail(email);
        if(!client.isPresent()) {
            throw ClientException.clientNotFound();
        }
        return client.get();
    }

    //returneaza toti clientii
    public Iterable<Client> findAllClients(){
        return clientRepo.findAll();
    }

    //adauga un client cu un numar de card aferent contului
    public Client addClient(String nume, String prenume, int varsta, String email, String telefon, String parola, String numar_card, String rol) throws DataIntegrityViolationException {
        Optional <Cont> cont=contRepo.findByNumarCard(numar_card);
        if(!cont.isPresent()){
            throw ClientException.accountNotFound();
        }
        return clientRepo.save(new Client(nume,prenume,varsta,email,telefon,parola,cont.get(),Client.Rol.valueOf(rol)));
    }
}
