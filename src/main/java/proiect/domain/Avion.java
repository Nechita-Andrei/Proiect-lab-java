package proiect.domain;


import javax.persistence.*;

@Entity
@Table(name = "AVION")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_AVION")
    @SequenceGenerator(name = "MY_GENERATOR_AVION", sequenceName = "ID_AVION", allocationSize = 1)
    private int id_avion;


    private String denumire;
    private short an_constructie;

    private int capacitate;
    @ManyToOne
    @JoinColumn(name = "id_companie_aeriana")
    private CompanieAeriana companieAerianaAvion;

    public Avion() {

    }

    public Avion(String denumire, short an_constructie, int capacitate, CompanieAeriana companieAerianaAvion) {
        this.denumire = denumire;
        this.an_constructie = an_constructie;
        this.capacitate = capacitate;
        this.companieAerianaAvion = companieAerianaAvion;
    }

    public int getId_avion() {
        return id_avion;
    }

    public String getDenumire() {
        return denumire;
    }

    public short getAn_constructie() {
        return an_constructie;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public CompanieAeriana getCompanieAerianaAvion() {
        return companieAerianaAvion;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setAn_constructie(short an_constructie) {
        this.an_constructie = an_constructie;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public void setCompanieAerianaAvion(CompanieAeriana companieAerianaAvion) {
        this.companieAerianaAvion = companieAerianaAvion;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "id_avion=" + id_avion +
                ", denumire='" + denumire + '\'' +
                ", an_constructie=" + an_constructie +
                ", capacitate=" + capacitate +
                ", companieAerianaAvion=" + companieAerianaAvion +
                '}';
    }
}
