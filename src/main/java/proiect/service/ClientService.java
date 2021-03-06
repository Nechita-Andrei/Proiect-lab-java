package proiect.service;


import proiect.domain.Adresa;
import proiect.domain.Client;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Cont;
import proiect.repository.AdresaRepo;
import proiect.repository.ClientRepo;
import proiect.repository.ContRepo;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    public ContRepo contRepo;

    @Autowired
    public AdresaRepo adresaRepo;

    //adaugare cont
    public Cont addCont(Cont cont){
        return contRepo.save(cont);
    }
    public Adresa addAdresa(Adresa adresa){
        return adresaRepo.save(adresa);
    }

    public void deleteClient(Integer user_id){
        Optional<Client>client=clientRepo.findById(user_id);
        if(!client.isPresent()) {
            throw ClientException.clientNotFound();
        }
        clientRepo.delete(client.get());
    }

    public Adresa findAdresaById(Integer id){
        Optional<Adresa> adresa=adresaRepo.findById(id);
        if(!adresa.isPresent()){
            throw ClientException.adressNotFound();
        }
        return adresa.get();
    }

    public Cont findContById(int id){
        Optional<Cont>cont=contRepo.findById(id);
        if(!cont.isPresent()){
            throw ClientException.accountNotFound();
        }
        return cont.get();
    }
    public Client getClientById(int id){
        Optional<Client>client=clientRepo.findById(id);
        if(!client.isPresent()) {
            throw ClientException.clientNotFound();
        }
        return client.get();
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
    public Client addClient(String nume, String prenume, int varsta, String email, String telefon, String parola, String numar_card, String rol, Integer adresa_id){
        Optional <Cont> cont=contRepo.findByNumarCard(numar_card);
        if(!cont.isPresent()){
            throw ClientException.accountNotFound();
        }
        Adresa adresa=this.findAdresaById(adresa_id);
        return clientRepo.save(new Client(nume,prenume,varsta,email,telefon,parola,cont.get(),Client.Rol.valueOf(rol),adresa));
    }
}
