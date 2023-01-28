package proiect.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COMPANIE_AERIANA")
public class CompanieAeriana {

    @Id
    @Column(name = "id_companie_aeriana")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_COMPANIE")
    @SequenceGenerator(name = "MY_GENERATOR_COMPANIE", sequenceName = "ID_COMPANIE_AERIANA", allocationSize = 1)
    private Integer idCompanieAeriana;

    @Column(unique = true, nullable = false)
    private String denumire;

    @Column
    private short anul_infiintarii;

    @OneToOne
    @JoinColumn(unique = true, name = "id_contact",referencedColumnName = "id_contact")
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
        return idCompanieAeriana;
    }

    public Set<Avion> getAvioane() {
        return avioane;
    }

    public void setAvioane(Set<Avion> avioane) {
        this.avioane = avioane;
    }

    @Override
    public String toString() {
        return "CompanieAeriana{" +
                "id_companie_aeriana=" + idCompanieAeriana +
                ", denumire='" + denumire + '\'' +
                ", anul_infiintarii=" + anul_infiintarii +
                ", contact=" + contact +

                '}';
    }
}
