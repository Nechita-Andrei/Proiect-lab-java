package proiect.domain;

import javax.persistence.*;

@Entity
@Table(name = "AEROPORT")
public class Aeroport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_AEROPORT")
    @SequenceGenerator(name = "MY_GENERATOR_AEROPORT", sequenceName = "ID_AEROPORT", allocationSize = 1)
    private int id_aeroport;

    @Column(unique = true)
    private String denumire;

    @Column
    private int an_constructie;

    @Column
    private int capacitate;

    @OneToOne
    @JoinColumn(name = "id_adresa",referencedColumnName = "id_adresa")
    private Adresa adresa;

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

    public int getAn_constructie() {
        return an_constructie;
    }

    public void setAn_constructie(int an_constructie) {
        this.an_constructie = an_constructie;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Aeroport(int id_aeroport, String denumire, int an_constructie, int capacitate, Adresa adresa) {
        this.id_aeroport = id_aeroport;
        this.denumire = denumire;
        this.an_constructie = an_constructie;
        this.capacitate = capacitate;
        this.adresa = adresa;
    }
}
