package proiect.domain;

import javax.persistence.*;

@Entity
@Table(name = "JUDET")
public class Judet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_JUDET")
    @SequenceGenerator(name = "MY_GENERATOR_JUDET", sequenceName = "ID_JUDET", allocationSize = 1)
    private Integer id_judet;

    private String denumire;
    private String abreviere;
    @ManyToOne
    @JoinColumn(name = "id_tara")
    private Tara tara;

    public Judet(){

    }
    public Judet(String denumire, String abreviere, Tara tara) {
        this.denumire = denumire;
        this.abreviere = abreviere;
        this.tara = tara;
    }

    public Integer getId_judet() {
        return id_judet;
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

    public Tara getTara() {
        return tara;
    }

    public void setTara(Tara tara) {
        this.tara = tara;
    }

    @Override
    public String toString() {
        return "Judet{" +
                "id_judet=" + id_judet +
                ", denumire='" + denumire + '\'' +
                ", abreviere='" + abreviere + '\'' +
                ", tara=" + tara +
                '}';
    }
}
