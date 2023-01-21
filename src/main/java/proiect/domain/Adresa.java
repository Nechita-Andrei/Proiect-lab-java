package proiect.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ADRESA")
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_ADRESA")
    @SequenceGenerator(name = "MY_GENERATOR_ADRESA", sequenceName = "ID_ADRESA", allocationSize = 1)
    private Integer id_adresa;

    @ManyToOne
    @JoinColumn(name = "id_oras")
    private Oras oras;

    @Column
    private String cod_postal;

    @Column(nullable = false)
    private String strada;

    public Adresa(Integer id_adresa, Oras oras, String cod_postal, String strada) {
        this.id_adresa = id_adresa;
        this.oras = oras;
        this.cod_postal = cod_postal;
        this.strada = strada;
    }

    public Adresa() {

    }

    public Integer getId_adresa() {
        return id_adresa;
    }

    public void setId_adresa(Integer id_adresa) {
        this.id_adresa = id_adresa;
    }

    public Oras getOras() {
        return oras;
    }

    public void setOras(Oras oras) {
        this.oras = oras;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }
}
