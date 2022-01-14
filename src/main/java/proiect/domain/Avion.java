package proiect.domain;


import javax.persistence.*;

@Entity
@Table(name = "avion")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String model;

    @Column(nullable = false)
    private int nr_locuri;

    public Avion() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNr_locuri() {
        return nr_locuri;
    }

    public void setNr_locuri(int nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    public int getAn_constructie() {
        return an_constructie;
    }

    public void setAn_constructie(int an_constructie) {
        this.an_constructie = an_constructie;
    }

    public Avion( String model, int nr_locuri, int an_constructie) {
        this.model = model;
        this.nr_locuri = nr_locuri;
        this.an_constructie = an_constructie;
    }

    @Column
    private int an_constructie;
}
