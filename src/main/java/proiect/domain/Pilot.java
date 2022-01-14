package proiect.domain;

import javax.persistence.*;


@Entity
@Table(name = "pilot")
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 20)
    private String nume;

    public Pilot() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public Experienta getExperienta() {
        return experienta;
    }

    public void setExperienta(Experienta experienta) {
        this.experienta = experienta;
    }

    public Pilot( String nume, String prenume, int varsta, double salariu, Experienta experienta) {
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.salariu = salariu;
        this.experienta = experienta;
    }

    @Column(nullable = false,length = 20)
    private String prenume;

    @Column
    private int varsta;

    @Column
    private double salariu;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Experienta experienta;

    public enum Experienta{
        INCEPATOR,
        MEDIU,
        AVANSAT,
        SENIOR
    }
}
