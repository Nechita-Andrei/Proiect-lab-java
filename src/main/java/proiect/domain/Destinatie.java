package proiect.domain;

import javax.persistence.*;

@Entity
@Table(name = "destinatie")
public class Destinatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String tara;

    public Destinatie() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public ZonaCovid getZona_covid() {
        return zona_covid;
    }

    public void setZona_covid(ZonaCovid zona_covid) {
        this.zona_covid = zona_covid;
    }

    public Destinatie( String tara, String localitate, ZonaCovid zona_covid) {
        this.tara = tara;
        this.localitate = localitate;
        this.zona_covid = zona_covid;
    }

    @Column
    private String localitate;

    @Column(name = "zona_covid",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ZonaCovid zona_covid;



    public enum ZonaCovid {
        ROSU,
        GALBEN,
        VERDE
    }
}
