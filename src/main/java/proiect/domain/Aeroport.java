package proiect.domain;

import javax.persistence.*;

@Entity
@Table(name = "AEROPORT")
public class Aeroport {

    @Id
    @Column(name = "id_aeroport")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_AEROPORT")
    @SequenceGenerator(name = "MY_GENERATOR_AEROPORT", sequenceName = "ID_AEROPORT", allocationSize = 1)
    private int idAeroport;

    @Column(unique = true, nullable = false)
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

    public int getIdAeroport() {
        return idAeroport;
    }

    public void setIdAeroport(int idAeroport) {
        this.idAeroport = idAeroport;
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

    public Aeroport(int idAeroport, String denumire, int an_constructie, int capacitate, Adresa adresa) {
        this.idAeroport = idAeroport;
        this.denumire = denumire;
        this.an_constructie = an_constructie;
        this.capacitate = capacitate;
        this.adresa = adresa;
    }
}
