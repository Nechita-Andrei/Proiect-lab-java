package proiect.domain;


import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "COMPANIE_AERIANA")
public class CompanieAeriana {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_generator_companie")
    @SequenceGenerator(name = "my_generator_companie", sequenceName = "id_companie_aeriana")
    private Integer id_companie_aeriana;


    private String denumire;

    private short anul_infiintarii;

    @OneToOne
    @JoinColumn(name = "id_contact",referencedColumnName = "id_contact")
    private Contact contact;

    @OneToMany(mappedBy = "companieAerianaPilot")
    private Set<Pilot> piloti;

    @OneToMany(mappedBy = "companieAerianaAvion")
    private Set<Avion> avioane;
    public CompanieAeriana(){

    }


    public CompanieAeriana(String denumire, short anul_infiintarii, Contact contact) {
        this.denumire = denumire;
        this.anul_infiintarii = anul_infiintarii;
        this.contact = contact;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public short getAnul_infiintarii() {
        return anul_infiintarii;
    }

    public void setAnul_infiintarii(short anul_infiintarii) {
        this.anul_infiintarii = anul_infiintarii;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Set<Pilot> getPiloti() {
        return piloti;
    }

    public void setPiloti(Set<Pilot> piloti) {
        this.piloti = piloti;
    }

    public Integer getId_companie_aeriana() {
        return id_companie_aeriana;
    }

    public Set<Avion> getAvioane() {
        return avioane;
    }

    public void setAvioane(Set<Avion> avioane) {
        this.avioane = avioane;
    }
}
