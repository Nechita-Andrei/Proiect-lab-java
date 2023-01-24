package proiect.domain;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ZBOR")
public class Zbor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_ZBOR")
    @SequenceGenerator(name = "MY_GENERATOR_ZBOR", sequenceName = "OLTP.ID_ZBOR", allocationSize = 1)
    @Column(name = "id_zbor")
    private Integer idZbor;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JsonIgnore
//    @JoinTable(name = "bilet", joinColumns = @JoinColumn(name = "id_zbor"), inverseJoinColumns = @JoinColumn(name = "id_client"))
//    private Set<Client> pasageri;

    @ManyToOne
    @JoinColumn(name = "id_aeroport_plecare",referencedColumnName = "id_aeroport")
    private Aeroport aeroport_plecare;

    @ManyToOne
    @JoinColumn(name = "id_aeroport_sosire",referencedColumnName = "id_aeroport")
    private Aeroport aeroport_sosire;

    @ManyToOne
    @JoinColumn(name = "id_pilot",referencedColumnName = "id_pilot")
    private Pilot pilot;

    @ManyToOne
    @JoinColumn(name = "id_copilot",referencedColumnName = "id_pilot")
    private Pilot copilot;

    @ManyToOne
    @JoinColumn(name = "id_companie_aeriana",referencedColumnName = "id_companie_aeriana")
    private CompanieAeriana companie_aeriana;

    @ManyToOne
    @JoinColumn(name = "id_avion",referencedColumnName = "id_avion")
    private Avion avion;

    @Column(nullable = false)
    private Timestamp data_plecare_estimata;

    @Column(nullable = false)
    private Timestamp data_sosire_estimata;

    @Column
    private Timestamp data_plecare_reala;

    @Column
    private Timestamp data_sosire_reala;


    public Zbor(Integer idZbor, Aeroport aeroport_plecare, Aeroport aeroport_sosire, Pilot pilot, Pilot copilot, CompanieAeriana companie_aeriana, Avion avion, Timestamp data_plecare_estimata, Timestamp data_sosire_estimata, Timestamp data_plecare_reala, Timestamp data_sosire_reala) {
        this.idZbor = idZbor;
        this.aeroport_plecare = aeroport_plecare;
        this.aeroport_sosire = aeroport_sosire;
        this.pilot = pilot;
        this.copilot = copilot;
        this.companie_aeriana = companie_aeriana;
        this.avion = avion;
        this.data_plecare_estimata = data_plecare_estimata;
        this.data_sosire_estimata = data_sosire_estimata;
        this.data_plecare_reala = data_plecare_reala;
        this.data_sosire_reala = data_sosire_reala;
    }

    public Zbor() {
    }

    public Integer getIdZbor() {
        return idZbor;
    }

    public void setIdZbor(Integer idZbor) {
        this.idZbor = idZbor;
    }


    public Aeroport getAeroport_plecare() {
        return aeroport_plecare;
    }

    public void setAeroport_plecare(Aeroport aeroport_plecare) {
        this.aeroport_plecare = aeroport_plecare;
    }

    public Aeroport getAeroport_sosire() {
        return aeroport_sosire;
    }

    public void setAeroport_sosire(Aeroport aeroport_sosire) {
        this.aeroport_sosire = aeroport_sosire;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Pilot getCopilot() {
        return copilot;
    }

    public void setCopilot(Pilot copilot) {
        this.copilot = copilot;
    }

    public CompanieAeriana getCompanie_aeriana() {
        return companie_aeriana;
    }

    public void setCompanie_aeriana(CompanieAeriana companie_aeriana) {
        this.companie_aeriana = companie_aeriana;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Timestamp getData_plecare_estimata() {
        return data_plecare_estimata;
    }

    public void setData_plecare_estimata(Timestamp data_plecare_estimata) {
        this.data_plecare_estimata = data_plecare_estimata;
    }

    public Timestamp getData_sosire_estimata() {
        return data_sosire_estimata;
    }

    public void setData_sosire_estimata(Timestamp data_sosire_estimata) {
        this.data_sosire_estimata = data_sosire_estimata;
    }

    public Timestamp getData_plecare_reala() {
        return data_plecare_reala;
    }

    public void setData_plecare_reala(Timestamp data_plecare_reala) {
        this.data_plecare_reala = data_plecare_reala;
    }

    public Timestamp getData_sosire_reala() {
        return data_sosire_reala;
    }

    public void setData_sosire_reala(Timestamp data_sosire_reala) {
        this.data_sosire_reala = data_sosire_reala;
    }

    @Override
    public String toString() {
        return "Zbor{" +
                "id=" + idZbor +
                ", aeroport_plecare=" + aeroport_plecare +
                ", aeroport_sosire=" + aeroport_sosire +
                ", pilot=" + pilot +
                ", copilot=" + copilot +
                ", companie_aeriana=" + companie_aeriana +
                ", avion=" + avion +
                ", data_plecare_estimata=" + data_plecare_estimata +
                ", data_sosire_estimata=" + data_sosire_estimata +
                ", data_plecare_reala=" + data_plecare_reala +
                ", data_sosire_reala=" + data_sosire_reala +
                '}';
    }
}
