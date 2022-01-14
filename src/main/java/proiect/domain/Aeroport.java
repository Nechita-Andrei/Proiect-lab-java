package proiect.domain;

import javax.persistence.*;

@Entity
@Table(name = "aeroport")
public class Aeroport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Aeroport() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
