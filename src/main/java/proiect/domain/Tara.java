package proiect.domain;

import javax.persistence.*;

@Entity
@Table(name = "TARA")
public class Tara {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_generator_tara")
    @SequenceGenerator(name = "my_generator_tara", sequenceName = "id_tara")
    private Integer id_tara;

    private String denumire;

    private String abreviere;

    public Tara(){

    }
    public Tara(String denumire, String abreviere) {
        this.denumire = denumire;
        this.abreviere = abreviere;
    }

    public Integer getId_tara() {
        return id_tara;
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

    @Override
    public String toString() {
        return "Tara{" +
                "id_tara=" + id_tara +
                ", denumire='" + denumire + '\'' +
                ", abreviere='" + abreviere + '\'' +
                '}';
    }
}
