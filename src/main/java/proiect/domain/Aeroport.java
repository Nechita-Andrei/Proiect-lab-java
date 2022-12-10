package proiect.domain;

import javax.persistence.*;

@Entity
@Table(name = "AEROPORT")
public class Aeroport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_AEROPORT")
    @SequenceGenerator(name = "MY_GENERATOR_AEROPORT", sequenceName = "ID_AEROPORT")
    private int id_aeroport;

    public Aeroport() {

    }

    public int getId_aeroport() {
        return id_aeroport;
    }

    public void setId_aeroport(int id_aeroport) {
        this.id_aeroport = id_aeroport;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public Aeroport( String denumire, String locatie, int capacitate) {
        this.denumire = denumire;
        this.locatie = locatie;
        this.capacitate = capacitate;
    }

    @Column(unique = true)
    private String denumire;

    @Column
    private String locatie;

    @Column
    private int capacitate;
}
