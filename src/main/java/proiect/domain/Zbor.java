package proiect.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "zbor")
public class Zbor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


//
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "bilet", joinColumns = @JoinColumn(name = "zbor_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> pasageri;
    public Zbor() {

    }

    @Column
    private int delay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aeroport getPlecare() {
        return plecare;
    }

    public void setPlecare(Aeroport plecare) {
        this.plecare = plecare;
    }

    public Aeroport getSosire() {
        return sosire;
    }

    public void setSosire(Aeroport sosire) {
        this.sosire = sosire;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Destinatie getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(Destinatie destinatie) {
        this.destinatie = destinatie;
    }

    public Date getData_plecare() {
        return data_plecare;
    }
    public Set<Client> getPasageri() {
        return pasageri;
    }
    public void setData_plecare(Date data_plecare) {
        this.data_plecare = data_plecare;
    }

    public void setPasageri(Set<Client> pasageri) {
        this.pasageri = pasageri;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Date getData_sosire() {
        return data_sosire;
    }

    public void setData_sosire(Date data_sosire) {
        this.data_sosire = data_sosire;
    }

    public Zbor( Aeroport plecare, Aeroport sosire, Avion avion, Pilot pilot, Destinatie destinatie, Date data_plecare, Date data_sosire, int pret) {
        this.plecare = plecare;
        this.sosire = sosire;
        this.avion = avion;
        this.pilot = pilot;
        this.destinatie = destinatie;
        this.data_plecare = data_plecare;
        this.data_sosire = data_sosire;
        this.pret=pret;
        this.delay=0;
    }

    @OneToOne
    @JoinColumn(name = "plecare_id",referencedColumnName = "id")
    private Aeroport plecare;

    @OneToOne
    @JoinColumn(name = "sosire_id",referencedColumnName = "id")
    private Aeroport sosire;

    @OneToOne
    @JoinColumn(name = "avion_id",referencedColumnName = "id_avion")
    private Avion avion;

    @OneToOne
    @JoinColumn(name = "pilot_id",referencedColumnName = "id_pilot")
    private Pilot pilot;

    @OneToOne
    @JoinColumn(name = "destinatie_id",referencedColumnName = "id")
    private Destinatie destinatie;

    public int getPret() {
        return pret;
    }

    @Column(nullable = false)
    private Date data_plecare;

    public int getDelay() {
        return delay;
    }

    @Column(nullable = false)
    private Date data_sosire;

    @Column(nullable = false)
    private int pret;

    public void addPasager(Client client){
        pasageri.add(client);
    }

}
