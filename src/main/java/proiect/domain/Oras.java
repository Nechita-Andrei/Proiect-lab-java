package proiect.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORAS")
public class Oras {

    @Id
    @Column(name = "id_oras")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_ORAS")
    @SequenceGenerator(name = "MY_GENERATOR_ORAS", sequenceName = "ID_ORAS")
    private Integer idOras;

    @ManyToOne
    @JoinColumn(name = "id_judet")
    private Judet judet;

    @ManyToOne
    @JoinColumn(name = "id_zona_covid")
    private ZonaCovid zona_covid;

    @Column(nullable = false)
    private String denumire;

    @Column
    private String abreviere;


    public Oras(Integer idOras, Judet judet, ZonaCovid zona_covid, String denumire, String abreviere) {
        this.idOras = idOras;
        this.judet = judet;
        this.zona_covid = zona_covid;
        this.denumire = denumire;
        this.abreviere = abreviere;
    }

    public Oras() {

    }

    public Integer getIdOras() {
        return idOras;
    }

    public void setIdOras(Integer idOras) {
        this.idOras = idOras;
    }

    public Judet getJudet() {
        return judet;
    }

    public void setJudet(Judet judet) {
        this.judet = judet;
    }

    public ZonaCovid getZona_covid() {
        return zona_covid;
    }

    public void setZona_covid(ZonaCovid zona_covid) {
        this.zona_covid = zona_covid;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getAbreviere() {
        return abreviere;
    }

    public void setAbreviere(String abreviere) {
        this.abreviere = abreviere;
    }
}
