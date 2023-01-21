package proiect.domain;


import javax.persistence.*;

@Entity
@Table(name = "TIP_BILET")
public class TipBilet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_TIP_BILET")
    @SequenceGenerator(name = "MY_GENERATOR_TIP_BILET", sequenceName = "ID_TIP_BILET", allocationSize = 1)
    private Integer id_tip_bilet;

    @Column(unique = true, nullable = false)
    private String denumire;

    @Column
    private String descriere;

    public TipBilet(){

    }
    public TipBilet(String denumire, String descriere) {
        this.denumire = denumire;
        this.descriere = descriere;
    }

    public Integer getId_tip_bilet() {
        return id_tip_bilet;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
